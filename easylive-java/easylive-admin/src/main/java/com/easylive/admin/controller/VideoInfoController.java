package com.easylive.admin.controller;

import com.easylive.entity.query.VideoInfoPostQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.service.VideoInfoPostService;
import com.easylive.service.VideoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/videoInfo")
@Validated
@Slf4j
public class VideoInfoController extends ABaseController{
    @Resource
    private VideoInfoPostService videoInfoPostService;
    @Resource
    private VideoInfoFilePostService videoInfoFilePostService;
    @Resource
    private VideoInfoService videoInfoService;

    //加载用户稿件
    @RequestMapping("/loadVideoList")
    public ResponseVO loadVideoPost(VideoInfoPostQuery videoInfoPostQuery){
        //最新更新的稿件排列在前
        videoInfoPostQuery.setOrderBy("v.last_update_time desc");
        videoInfoPostQuery.setQueryCountInfo(true);
        videoInfoPostQuery.setQueryUserInfo(true);
        PaginationResultVO resultVO = videoInfoPostService.findListByPage(videoInfoPostQuery);
        return getSuccessResponseVO(resultVO);
    }

    @RequestMapping("/auditVideo")
    public ResponseVO auditVideo(@NotEmpty String videoId, @NotNull Integer status, String reason){
        videoInfoPostService.auditVideo(videoId,status,reason);
        return getSuccessResponseVO(null);
    }


}
