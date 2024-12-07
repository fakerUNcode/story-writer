package com.easylive.web.controller;

import com.easylive.web.annotation.GlobalInterceptor;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.po.UserVideoSeries;
import com.easylive.entity.po.UserVideoSeriesVideo;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.UserVideoSeriesQuery;
import com.easylive.entity.query.UserVideoSeriesVideoQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.UserVideoSeriesDetailVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserVideoSeriesService;
import com.easylive.service.UserVideoSeriesVideoService;
import com.easylive.service.VideoInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @GlobalInterceptor(checkLogin = true)
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
    @GlobalInterceptor(checkLogin = true)
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

    //获取视频合集中的视频详情
    @RequestMapping("/getVideoSeriesDetail")
    public ResponseVO getVideoSeriesDetail(@NotNull Integer seriesId) {
        UserVideoSeries videoSeries = userVideoSeriesService.getUserVideoSeriesBySeriesId(seriesId);
        if(videoSeries==null){
            throw new BusinessException(ResponseCodeEnum.CODE_404);
        }

        UserVideoSeriesVideoQuery videoSeriesVideoQuery = new UserVideoSeriesVideoQuery();
        videoSeriesVideoQuery.setOrderBy("sort asc");
        videoSeriesVideoQuery.setQueryVideoInfo(true);
        videoSeriesVideoQuery.setSeriesId(seriesId);
        List<UserVideoSeriesVideo> seriesVideoList = userVideoSeriesVideoService.findListByParam(videoSeriesVideoQuery);
        // // 封装系列信息和视频列表
        UserVideoSeriesDetailVO userVideoSeriesDetailVO = new  UserVideoSeriesDetailVO(videoSeries,seriesVideoList);
        return getSuccessResponseVO(userVideoSeriesDetailVO);
    }

    //向合集添加视频
    @RequestMapping("/saveSeriesVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO saveSeriesVideo(@NotNull Integer seriesId,@NotEmpty String videoIds) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        this.userVideoSeriesService.saveSeriesVideo(tokenUserInfoDto.getUserId(),seriesId,videoIds);
        return getSuccessResponseVO(null);
    }

    //删除合集内的视频
    @RequestMapping("/delSeriesVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delSeriesVideo(String userId,Integer seriesId,String videoId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        this.userVideoSeriesService.delSeriesVideo(tokenUserInfoDto.getUserId(),seriesId,videoId);
        return getSuccessResponseVO(null);
    }

    //删除视频集合
    @RequestMapping("/delVideoSeries")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delVideoSeries(@NotNull Integer seriesId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        this.userVideoSeriesService.delVideoSeries(tokenUserInfoDto.getUserId(),seriesId);
        return getSuccessResponseVO(null);
    }

    //视频集合排序
    @RequestMapping("/changeVideoSeriesSort")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO changeVideoSeriesSort(@NotNull String seriesIds) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        this.userVideoSeriesService.changeVideoSeriesSort(tokenUserInfoDto.getUserId(),seriesIds);
        return getSuccessResponseVO(null);
    }

    //加载用户主页的“我的视频列表”（包含视频展示）
    @RequestMapping("/loadVideoSeriesWithVideo")
    public ResponseVO loadVideoSeriesWithVideo(@NotNull String userId) {
        UserVideoSeriesQuery seriesQuery = new UserVideoSeriesQuery();
        seriesQuery.setUserId(userId);
        seriesQuery.setOrderBy("sort asc");
        List<UserVideoSeries> videoSeries = userVideoSeriesService.findListWithVideoList(seriesQuery);
        return getSuccessResponseVO(videoSeries);
    }

}
