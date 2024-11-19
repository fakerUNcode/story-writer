package com.easylive.web.controller;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserInfoService;
import com.easylive.utils.StringTools;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息表 Controller
 */
@RestController
@RequestMapping("/account")
@Validated
public class AccountController extends ABaseController{

	@Resource
	private UserInfoService userInfoService;
	@Resource
	private RedisComponent redisComponent;
	@RequestMapping("/checkCode")
	public ResponseVO checkCode(){
		ArithmeticCaptcha captcha = new ArithmeticCaptcha(100,42);
		String code = captcha.text();
		String checkCodeKey = redisComponent.saveCheckCode(code);
		String checkCodeBase64 = captcha.toBase64();

		Map<String,String> result = new HashMap<>();
		result.put("checkCode",checkCodeBase64);
		result.put("checkCodeKey",checkCodeKey);
		return getSuccessResponseVO(result);
	}
	@RequestMapping("/register")
	public ResponseVO register(@NotEmpty @Email @Size(max=150) String email,
							   @NotEmpty @Size(max=20) String nickName,
							   @NotEmpty @Pattern(regexp = Constants.REGEX_PASSWORD) String registerPassword,
							   @NotEmpty String checkCodeKey,
							   @NotEmpty String checkCode){
		try {
			if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
				throw new BusinessException("图形验证码错误！");
			}
			userInfoService.register(email,nickName,registerPassword);
			return getSuccessResponseVO(null);
		}finally {
			redisComponent.cleanCheckCode(checkCodeKey);
		}
	}

	@RequestMapping("/login")
	public ResponseVO login(HttpServletRequest request,
							HttpServletResponse response,
							@NotEmpty @Email String email,
							@NotEmpty String password,
							@NotEmpty String checkCodeKey,
							@NotEmpty String checkCode){
		try {
			if(!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey))){
				throw new BusinessException("图形验证码错误！");
			}
			String ip = getIpAddr();
			TokenUserInfoDto tokenUserInfoDto = userInfoService.login(email,password,ip);
			saveToken2Cookie(response,tokenUserInfoDto.getToken());
			//TODO 粉丝等数据
			return getSuccessResponseVO(tokenUserInfoDto);
		}finally {
			redisComponent.cleanCheckCode(checkCodeKey);
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				String token = null;
				for(Cookie cookie : cookies){
					if(cookie.getName().equals(Constants.TOKEN_WEB)){
						token = cookie.getValue();
					}
				}
				if (!StringTools.isEmpty(token)){
					redisComponent.cleanToken(token);
				}
			}
		}
	}

	@RequestMapping("/autologin")
	public ResponseVO autoLogin(HttpServletResponse response){
		TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
		//如果为空说明长时间未登录token已清空，需重新登录
		if(tokenUserInfoDto==null){
			return getSuccessResponseVO(null);
		}
		//如果用户在过期前一天登录，则刷新有效时间从新登录时刻起计算
		if(tokenUserInfoDto.getExpireAt() - System.currentTimeMillis() < Constants.REDIS_KEY_EXPIRES_ONE_DAY){
			redisComponent.saveTokenInfo(tokenUserInfoDto);// 保存新的 Token 信息到 Redis
			saveToken2Cookie(response,tokenUserInfoDto.getToken());// 更新新的token信息到用户浏览器的Cookie中
			//上述操作使Redis中存储的新token和用户浏览器中刷新的token进行同步
		}
		//即使当前 Token 的有效期足够长，没有刷新 Token 的必要，也需要确保客户端的 Cookie 中正确存储了用户的 Token。
		saveToken2Cookie(response,tokenUserInfoDto.getToken());
		return getSuccessResponseVO(tokenUserInfoDto);
	}

	@RequestMapping("/logout")
	public ResponseVO logout(HttpServletResponse response){
		cleanCookie(response);
		return getSuccessResponseVO(null);
	}
}