package com.easylive.admin.interceptor;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.exception.BusinessException;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.easylive.entity.enums.ResponseCodeEnum.CODE_901;

/*一个自定义的 Spring MVC 拦截器，用于拦截 HTTP 请求并执行一些预定义的逻辑。*/
@Component
public class AppInterceptor implements HandlerInterceptor {
    private final static String URL_ACCOUNT = "/account";
    private final static String URL_FILE ="/file";

    @Resource
    private RedisComponent redisComponent;

    //preHandle 方法会在每个请求到达控制器（Controller）之前执行
    //验证登录的管理员：获取客户端的身份令牌 (token) 并验证其是否存在或有效。如果 token 不存在，则抛出异常，表示未登录或登录超时。
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(null==handler){
            return false;
        }
        //检查 handler 是否是 HandlerMethod（即是否是一个处理请求的控制器方法）。如果不是（例如静态资源请求），直接放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //如果请求路径包含 /account，直接放行
        if(request.getRequestURI().contains("/account")){
            return true;
        }
        //从请求头获取用户浏览器的身份令牌 (token) 并验证其是否存在或有效。如果 token 不存在，则抛出异常，表示未登录或登录超时。
        String token = request.getHeader(Constants.TOKEN_ADMIN);
        if(request.getRequestURI().contains(URL_FILE)){
            token = getTokenFromCookie(request);
        }
        if(StringTools.isEmpty(token)){
            throw new BusinessException(CODE_901);
        }
        Object sessionObj = redisComponent.getTokenInfo4Admin(token);
        //如果从 Redis 中获取的 sessionObj 为 null，表明 token 无效或超时。
        if(null==sessionObj){
            throw new BusinessException(CODE_901);
        }
        return true;
    }

    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[ ] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(Constants.TOKEN_ADMIN)){
                return cookie.getValue();
            }
        }
        return null;
    }

    /*postHandle 方法会在控制器处理完请求并返回视图之前执行
    * 可以用来修改模型数据（ModelAndView）或在视图返回之前执行一些逻辑*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
   /* afterCompletion 方法会在整个请求完成之后执行（视图渲染之后）
   * 通常用于清理资源或记录请求处理的最终状态（如日志记录）*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
