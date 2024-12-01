package com.easylive.web.controller;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.UserVideoSeries;
import com.easylive.entity.po.UserVideoSeriesVideo;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.UserVideoSeriesVideoQuery;
import com.easylive.entity.query.VideoInfoQuery;
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
import java.util.stream.Collectors;

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
                                      @NotEmpty @Size(max=100)String seriesName,
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

    //加载集合的所有视频
    @RequestMapping("/loadAllVideo")
    public ResponseVO loadAllVideo(Integer seriesId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        VideoInfoQuery infoQuery = new VideoInfoQuery();

        // 如果传入了 seriesId 参数，则表示需要查询特定系列的视频进行过滤
        if (seriesId != null) {
            UserVideoSeriesVideoQuery videoSeriesVideoQuery = new UserVideoSeriesVideoQuery();
            videoSeriesVideoQuery.setSeriesId(seriesId);
            videoSeriesVideoQuery.setUserId(tokenUserInfoDto.getUserId());
            List<UserVideoSeriesVideo> seriesVideoList = userVideoSeriesVideoService.findListByParam(videoSeriesVideoQuery);

            List<String> videoIdList = seriesVideoList.stream()
                    .map(item->item.getVideoId())
                    .collect(Collectors.toList());
            infoQuery.setExcludeVideoIdArray(videoIdList.toArray(new String[videoIdList.size()]));
        }

        infoQuery.setUserId(tokenUserInfoDto.getUserId());
        List<VideoInfo> videoInfoList = videoInfoService.findListByParam(infoQuery);

        return getSuccessResponseVO(videoInfoList);
    }



}
