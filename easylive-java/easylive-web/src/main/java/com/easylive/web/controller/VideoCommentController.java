package com.easylive.web.controller;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.CommentTopTypeEnum;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoComment;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoCommentResultVO;
import com.easylive.service.UserActionService;
import com.easylive.service.VideoCommentService;
import com.easylive.service.VideoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
@Slf4j
@Validated
public class VideoCommentController extends ABaseController{
    @Resource
    private VideoCommentService videoCommentService;
    @Resource
    private UserActionService userActionService;
    @Resource
    private VideoInfoService videoInfoService;

    @RequestMapping("/postComment")
    public ResponseVO postComment(
            @NotEmpty String videoId,
            @NotEmpty @Size(max = 500) String content,
            Integer replyCommentId,
            @Size(max = 50) String imgPath) {

        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        VideoComment comment = new VideoComment();
        comment.setUserId(tokenUserInfoDto.getUserId());
        comment.setAvatar(tokenUserInfoDto.getAvatar());
        comment.setNickName(tokenUserInfoDto.getNickName());
        comment.setVideoId(videoId);
        comment.setContent(content);
        comment.setImgPath(imgPath);

        videoCommentService.postComment(comment, replyCommentId);
        return getSuccessResponseVO(comment);
    }

    @RequestMapping("/loadComment")
    public ResponseVO loadComment(
            @NotEmpty String videoId,
            Integer pageNo,
            Integer orderType) {

        VideoInfo videoInfo = videoInfoService.getVideoInfoByVideoId(videoId);
        //如果视频的 interaction 属性中包含 0，说明评论区被关闭，直接返回空数据
        if(videoInfo.getInteraction()!=null && videoInfo.getInteraction().contains(Constants.ZERO.toString())){
            return getSuccessResponseVO(new ArrayList<>());
        }
        //定义查询条件对象
        VideoCommentQuery commentQuery = new VideoCommentQuery();
        commentQuery.setVideoId(videoId);
        commentQuery.setLoadChildren(true);
        commentQuery.setPageNo(pageNo);
        commentQuery.setPageSize(PageSize.SIZE15.getSize());
        commentQuery.setpCommentId(0);

        //默认情况按评论id先后排序
        String orderBy = orderType==null||orderType==0?"like_count desc,comment_id desc":"comment_id desc";
        commentQuery.setOrderBy(orderBy);
        PaginationResultVO<VideoComment> commentData = videoCommentService.findListByPage(commentQuery);

        //如果是第一页，并且该视频有置顶评论，将置顶评论添加到评论列表的最前面。
        if(pageNo==null){
            List<VideoComment> topCommentList = topComment(videoId);
            if(!topCommentList.isEmpty()){
                //过滤掉置顶评论，以免重复显示：
                List<VideoComment> commentList =
                        commentData.getList().stream().filter(item->!item.getCommentId().equals(topCommentList.get(0).getCommentId())).collect(Collectors.toList());
                //将置顶评论插入到评论列表的最前面
                commentList.addAll(0,topCommentList);
                commentData.setList(commentList);
            }
        }

        VideoCommentResultVO resultVO = new VideoCommentResultVO();
        resultVO.setCommentData(commentData);
        //同时将用户行为也传入VO
        List<UserAction> userActionList = new ArrayList<>();
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        if(tokenUserInfoDto!=null){
            UserActionQuery actionQuery = new UserActionQuery();
            actionQuery.setVideoId(videoId);
            actionQuery.setUserId(tokenUserInfoDto.getUserId());
            actionQuery.setActionTypeArray(new Integer[] {UserActionTypeEnum.COMMENT_LIKE.getType(),UserActionTypeEnum.COMMENT_HATE.getType()});
            userActionList = userActionService.findListByParam(actionQuery);
        }

        resultVO.setUserActionList(userActionList);

        return getSuccessResponseVO(resultVO);
    }

    //评论置顶
    private List<VideoComment> topComment(String videoId){
        VideoCommentQuery commentQuery = new VideoCommentQuery();
        commentQuery.setVideoId(videoId);
        commentQuery.setTopType(CommentTopTypeEnum.TOP.getType());
        commentQuery.setLoadChildren(true);
        List<VideoComment> videoCommentList = videoCommentService.findListByParam(commentQuery);
        return videoCommentList;
    }

}
