package com.easylive.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

//DTO类设置为Data Transfer Object 此DTO用于在系统内部传递与用户相关的信息，尤其是通过令牌（Token）解析后获取的用户信息
//该类标识用户的登录会话，传递了用户的粉丝数、关注数等信息，以便登录后查看信息

//注解：用于忽略 JSON 中未知的属性，确保在反序列化 JSON 数据时，即使传递的 JSON 包含未定义的字段，也不会报错。
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUserInfoDto implements Serializable {
    private static final long serialVersionUID = 9170480547933408839L;
    private String userId;
    private String nickName;
    //用户头像
    private String avatar;
    //token失效时间
    private Long expireAt;
    private String token;
    //粉丝用户数、当前硬币数、关注用户数
    private Integer fansCount;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    private Integer currentCoinCount;
    private Integer focusCount;

    public String getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public Integer getCurrentCoinCount() {
        return currentCoinCount;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public void setCurrentCoinCount(Integer currentCoinCount) {
        this.currentCoinCount = currentCoinCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

}
