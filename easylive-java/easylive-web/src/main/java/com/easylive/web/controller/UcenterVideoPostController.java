package com.easylive.web.controller;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.VideoStatusEnum;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.VideoInfoPostQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoStatusCountVO;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.service.VideoInfoPostService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/ucenter")
@Validated
@Slf4j
public class UcenterVideoPostController extends ABaseController{
    @Resource
    private VideoInfoPostService videoInfoPostService;
    @Resource
    private VideoInfoFilePostService videoInfoFilePostService;
    @Resource
    private VideoInfoService videoInfoService;

    @RequestMapping("/postVideo")
    public ResponseVO postVideo(String videoId,
                                @NotEmpty String videoCover,
                                @NotEmpty @Size(max = 100) String videoName,
                                @NotNull Integer pCategoryId,
                                Integer categoryId,
                                @NotNull Integer postType,
                                @NotEmpty @Size(max = 300)String tags,
                                @Size(max = 2000) String introduction,
                                @Size(max = 3) String interaction,
                                @NotEmpty String uploadFileList){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        List<VideoInfoFilePost> filePostList = JsonUtils.convertJsonArray2List(uploadFileList, VideoInfoFilePost.class);

        VideoInfoPost videoInfoPost = new VideoInfoPost();
        videoInfoPost.setUserId(tokenUserInfoDto.getUserId());
        videoInfoPost.setVideoId(videoId);
        videoInfoPost.setVideoCover(videoCover);
        videoInfoPost.setVideoName(videoName);
        videoInfoPost.setpCategoryId(pCategoryId);
        videoInfoPost.setCategoryId(categoryId);
        videoInfoPost.setPostType(postType);
        videoInfoPost.setTags(tags);
        videoInfoPost.setIntroduction(introduction);
        videoInfoPost.setInteraction(interaction);

        videoInfoPostService.saveVideoInfo(videoInfoPost, filePostList);

        return getSuccessResponseVO(null);
    }

    //加载用户稿件
    @RequestMapping("/loadVideoList")
    public ResponseVO loadVideoPost(Integer status, Integer pageNo, String videoNameFuzzy){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        VideoInfoPostQuery videoInfoQuery = new VideoInfoPostQuery();
        videoInfoQuery.setUserId(tokenUserInfoDto.getUserId());
        videoInfoQuery.setOrderBy("v.create_time desc");
        videoInfoQuery.setPageNo(pageNo);
        if(status!=null){
            //状态-1:审核进行中的稿件
            if(status==-1){
                //排除审核成功的稿件和审核不通过的稿件
                videoInfoQuery.setExcludeStatusArray(new Integer[]{VideoStatusEnum.STATUS3.getStatus(),VideoStatusEnum.STATUS4.getStatus()});
            }else {
                videoInfoQuery.setStatus(status);
            }
        }
        videoInfoQuery.setVideoNameFuzzy(videoNameFuzzy);
        videoInfoQuery.setQueryCountInfo(true);
        PaginationResultVO resultVO = videoInfoPostService.findListByPage(videoInfoQuery);

        return getSuccessResponseVO(resultVO);
    }

    //用户的稿件中各个状态的稿件的数量获取
    @RequestMapping("/getVideoCountInfo")
    public ResponseVO getVideoCountInfo(){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();

        VideoInfoPostQuery videoInfoPostQuery = new VideoInfoPostQuery();
        videoInfoPostQuery.setUserId(tokenUserInfoDto.getUserId());
        //审核成功的视频
        videoInfoPostQuery.setStatus(VideoStatusEnum.STATUS3.getStatus());
        Integer auditPassCount = videoInfoPostService.findCountByParam(videoInfoPostQuery);
        //审核失败的视频
        videoInfoPostQuery.setStatus(VideoStatusEnum.STATUS4.getStatus());
        Integer auditFailCount = videoInfoPostService.findCountByParam(videoInfoPostQuery);
        //审核进行中的视频
        videoInfoPostQuery.setStatus(null);
        videoInfoPostQuery.setExcludeStatusArray(new Integer[]{VideoStatusEnum.STATUS3.getStatus(),VideoStatusEnum.STATUS4.getStatus()});
        Integer inProgress = videoInfoPostService.findCountByParam(videoInfoPostQuery);
        VideoStatusCountVO countVO = new VideoStatusCountVO();
        countVO.setAuditPassCount(auditPassCount);
        countVO.setAuditFailCount(auditFailCount);
        countVO.setInProgress(inProgress);
        return getSuccessResponseVO(countVO);
    }
}
