package com.easylive.entity.dto;

import java.io.Serializable;

//系统设定类数据传输对象
public class SysSettingDto implements Serializable {
    private static final long serialVersionUID = -90520876542443938L;
    //注册赠送10硬币
    private Integer registerCoinCount = 10;
    //每发布一个视频赠送5硬币
    private Integer postVideoCoinCount = 5;
    private Integer videoSize = 10;
    //视频分p数
    private Integer videoPCount = 10;
    //视频数
    private Integer videoCount = 10;
    //评论数
    private Integer commentCount = 20;
    //弹幕数
    private Integer danmuCount = 20;

    public Integer getRegisterCoinCount() {
        return registerCoinCount;
    }

    public void setRegisterCoinCount(Integer registerCoinCount) {
        this.registerCoinCount = registerCoinCount;
    }

    public Integer getPostVideoCoinCount() {
        return postVideoCoinCount;
    }

    public void setPostVideoCoinCount(Integer postVideoCoinCount) {
        this.postVideoCoinCount = postVideoCoinCount;
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    public Integer getVideoPCount() {
        return videoPCount;
    }

    public void setVideoPCount(Integer videoPCount) {
        this.videoPCount = videoPCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getDanmuCount() {
        return danmuCount;
    }

    public void setDanmuCount(Integer danmuCount) {
        this.danmuCount = danmuCount;
    }
}
