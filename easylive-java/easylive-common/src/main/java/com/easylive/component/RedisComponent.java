package com.easylive.component;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

}
