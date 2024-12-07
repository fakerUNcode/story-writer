package com.easylive.web.controller;

import com.easylive.web.annotation.GlobalInterceptor;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.VideoCommentService;
import com.easylive.service.VideoDanmuService;
import com.easylive.service.VideoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/*用户创作中心互动管理 Controller*/
@RestController
@RequestMapping("/ucenter")
@Validated
@Slf4j
public class UcenterInteractionController extends ABaseController{
    @Resource
    private VideoInfoService videoInfoService;
    @Resource
    private VideoCommentService videoCommentService;
    @Resource
    private VideoDanmuService videoDanmuService;

    //获取视频信息
    @RequestMapping("/loadAllVideo")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadAllVideo(){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();

        VideoInfoQuery videoInfoQuery =new VideoInfoQuery();
        videoInfoQuery.setUserId(tokenUserInfoDto.getUserId());
        videoInfoQuery.setOrderBy("create_time desc");
        List<VideoInfo> videoInfoList = videoInfoService.findListByParam(videoInfoQuery);
        return getSuccessResponseVO(videoInfoList);
    }

    //加载视频评论管理
    @RequestMapping("/loadComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadComment(Integer pageNo,String videoId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();

        VideoCommentQuery videoCommentQuery = new VideoCommentQuery();
        videoCommentQuery.setVideoId(videoId);
        videoCommentQuery.setVideoUserId(tokenUserInfoDto.getUserId());
        videoCommentQuery.setOrderBy("comment_id desc");
        videoCommentQuery.setPageSize(pageNo);
        videoCommentQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO = videoCommentService.findListByPage(videoCommentQuery);
        return getSuccessResponseVO(resultVO);
    }

    //删除视频评论
    @RequestMapping("/delComment")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delComment(@NotNull Integer commentId) {
        videoCommentService.deleteComment(commentId,getTokenUserInfoDto().getUserId());
        return getSuccessResponseVO(null);
    }

    //加载视频弹幕管理
    @RequestMapping("/loadDanmu")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO loadDanmu( Integer pageNo,String videoId) {
       TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        VideoDanmuQuery danmuQuery = new VideoDanmuQuery();
        danmuQuery.setVideoId(videoId);
        danmuQuery.setVideoUserId(tokenUserInfoDto.getUserId());
        danmuQuery.setOrderBy("danmu_id desc");
        danmuQuery.setPageNo(pageNo);
        danmuQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO = videoDanmuService.findListByPage(danmuQuery);
        return getSuccessResponseVO(resultVO);
    }

    //删除视频弹幕
    @RequestMapping("/delDanmu")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO delDanmu(@NotNull Integer danmuId) {
        videoDanmuService.deleteDanmu(getTokenUserInfoDto().getUserId(),danmuId);
        return getSuccessResponseVO(null);
    }

}
