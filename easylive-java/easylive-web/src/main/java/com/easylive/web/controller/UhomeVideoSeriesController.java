package com.easylive.web.controller;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.UserVideoSeries;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserVideoSeriesService;
import com.easylive.service.UserVideoSeriesVideoService;
import com.easylive.service.VideoInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/uhome/series")
@Validated
public class UhomeVideoSeriesController extends ABaseController{
    @Resource
    private VideoInfoService videoInfoService;
    @Resource
    private UserVideoSeriesService userVideoSeriesService;
    @Resource
    private UserVideoSeriesVideoService userVideoSeriesVideoService;

    //加载用户视频集合
    @RequestMapping("/loadVideoSeries")
    public ResponseVO loadVideoSeries(@NotEmpty String userId){
        List<UserVideoSeries> videoSeries = userVideoSeriesService.getUserAllSeries(userId);

        return getSuccessResponseVO(videoSeries);
    }

    //创建用户视频集合
    @RequestMapping("/saveVideoSeries")
    public ResponseVO saveVideoSeries(Integer seriesId,
                                      @Size(max=100)String seriesName,
                                      @Size(max=200)String seriesDescription,
                                      String videoIds){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();

        UserVideoSeries videoSeries = new UserVideoSeries();
        videoSeries.setUserId(tokenUserInfoDto.getUserId());
        videoSeries.setSeriesId(seriesId);
        videoSeries.setSeriesName(seriesName);
        videoSeries.setSeriesDescription(seriesDescription);

        this.userVideoSeriesService.saveUserVideoSeries(videoSeries,videoIds);

        return getSuccessResponseVO(videoSeries);
    }




}
