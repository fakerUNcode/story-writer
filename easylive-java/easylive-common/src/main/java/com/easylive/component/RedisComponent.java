package com.easylive.component;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static com.easylive.entity.constants.Constants.REDIS_KEY_EXPIRES_ONE_MIN;


@Component
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;
    public String saveCheckCode(String code){
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey, code, REDIS_KEY_EXPIRES_ONE_MIN*10);
        return checkCodeKey;
    }

    public String getCheckCode(String checkCodeKey){
        return (String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    //刷新验证码
    public void cleanCheckCode(String checkCodeKey){
        redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    public void saveTokenInfo(TokenUserInfoDto tokenUserInfoDto){
        String token = UUID.randomUUID().toString();
        //用户登录的token有效期为7天，7天后需要重新登录
        tokenUserInfoDto.setExpireAt(System.currentTimeMillis() + Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
        tokenUserInfoDto.setToken(token);
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_WEB + token, tokenUserInfoDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }



    public void cleanToken(String token){
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_WEB + token);
    }

    public TokenUserInfoDto getTokenInfo(String token){
        return (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_TOKEN_WEB+token);
    }

    public String getTokenInfo4Admin(String token){
        return (String) redisUtils.get(Constants.REDIS_KEY_TOKEN_ADMIN+token);
    }

    public String saveTokenInfo4Admin(String account){
        String token = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_ADMIN+token,account,Constants.REDIS_KEY_EXPIRES_ONE_DAY);
        return token;
    }

    public void cleanToken4Admin(String token){
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_ADMIN + token);
    }

    //将CategoryInfo列表存入Redis缓存，以便后续使用
    public void saveCategoryList(List<CategoryInfo> categoryInfoList){
        redisUtils.set(Constants.REDIS_KEY_CATEGORY_LIST, categoryInfoList);
    }

}
