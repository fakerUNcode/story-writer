package com.easylive.entity.vo;

import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.po.VideoInfoPost;

import java.util.List;


/*创作中心视频信息封装*/
public class VideoPostEditInfoVO {
    private VideoInfoPost videoInfo;
    private List<VideoInfoFilePost> videoInfoFileList;

    public VideoInfoPost getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfoPost videoInfo) {
        this.videoInfo = videoInfo;
    }

    public List<VideoInfoFilePost> getVideoInfoFileList() {
        return videoInfoFileList;
    }

    public void setVideoInfoFileList(List<VideoInfoFilePost> videoInfoFileList) {
        this.videoInfoFileList = videoInfoFileList;
    }
}
