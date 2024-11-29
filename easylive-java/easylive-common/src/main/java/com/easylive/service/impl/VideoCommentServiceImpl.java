package com.easylive.service.impl;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.CommentTopTypeEnum;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.po.VideoComment;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.query.VideoCommentQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.UserInfoMapper;
import com.easylive.mappers.VideoCommentMapper;
import com.easylive.mappers.VideoInfoMapper;
import com.easylive.service.VideoCommentService;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 评论 业务接口实现
 */
@Service("videoCommentService")
public class VideoCommentServiceImpl implements VideoCommentService {

	@Resource
	private VideoCommentMapper<VideoComment, VideoCommentQuery> videoCommentMapper;
	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoComment> findListByParam(VideoCommentQuery param) {
		if(param.getLoadChildren()!=null && param.getLoadChildren()){
			return this.videoCommentMapper.selectListWithChildren(param);
		}
		return this.videoCommentMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoCommentQuery param) {
		return this.videoCommentMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoComment> findListByPage(VideoCommentQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoComment> list = this.findListByParam(param);
		PaginationResultVO<VideoComment> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoComment bean) {
		return this.videoCommentMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoComment> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoCommentMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoComment> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoCommentMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoComment bean, VideoCommentQuery param) {
		StringTools.checkParam(param);
		return this.videoCommentMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoCommentQuery param) {
		StringTools.checkParam(param);
		return this.videoCommentMapper.deleteByParam(param);
	}

	/**
	 * 根据CommentId获取对象
	 */
	@Override
	public VideoComment getVideoCommentByCommentId(Integer commentId) {
		return this.videoCommentMapper.selectByCommentId(commentId);
	}

	/**
	 * 根据CommentId修改
	 */
	@Override
	public Integer updateVideoCommentByCommentId(VideoComment bean, Integer commentId) {
		return this.videoCommentMapper.updateByCommentId(bean, commentId);
	}

	/**
	 * 根据CommentId删除
	 */
	@Override
	public Integer deleteVideoCommentByCommentId(Integer commentId) {
		return this.videoCommentMapper.deleteByCommentId(commentId);
	}

	@Override
	public void postComment(VideoComment comment, Integer replyCommentId) {
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(comment.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		if(videoInfo.getInteraction()!=null && videoInfo.getInteraction().contains(Constants.ZERO.toString())){
			throw new BusinessException("up主已经关闭评论区！");
		}
		if(replyCommentId!=null){
			VideoComment replyComment = getVideoCommentByCommentId(replyCommentId);
			if(replyComment==null || !replyComment.getVideoId().equals(comment.getVideoId())){
				throw new BusinessException(ResponseCodeEnum.CODE_600);
			}
			//如果要评论的是视频的一级评论，则将新加的评论的父评论设为一级评论，如果评论二级评论，则父评论都是一级评论
			if(replyComment.getpCommentId()==0){
				comment.setpCommentId(replyComment.getCommentId());
			}else{
				comment.setpCommentId(replyComment.getpCommentId());
				comment.setReplyUserId(replyComment.getUserId());
			}
			UserInfo userInfo = userInfoMapper.selectByUserId(replyComment.getUserId());
			comment.setReplyNickName(userInfo.getNickName());
			comment.setReplyAvatar(userInfo.getAvatar());
		} else {
			//直接评论视频，该评论为一级评论
			comment.setpCommentId(0);
		}
		comment.setPostTime(new Date());
		comment.setVideoUserId(videoInfo.getUserId());
		this.videoCommentMapper.insert(comment);
		//是一级评论则让视频的评论数信息更新
		if(comment.getpCommentId()==0){
			this.videoInfoMapper.updateCountInfo(comment.getVideoId(), UserActionTypeEnum.VIDEO_COMMENT.getField(), 1);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void topComment(Integer commentId, String userId) {
		this.cancelTopComment(commentId,userId);
		VideoComment videoComment = new VideoComment();
		videoComment.setTopType(CommentTopTypeEnum.TOP.getType());
		videoCommentMapper.updateByCommentId(videoComment,commentId);
	}

	@Override
	public void cancelTopComment(Integer commentId, String userId) {
		VideoComment dbVideoComment = videoCommentMapper.selectByCommentId(commentId);
		//没有被置顶的评论不允许取消置顶
		if(dbVideoComment==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(dbVideoComment.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		//只允许视频发布者更改置顶信息
		if(!videoInfo.getUserId().equals(userId)){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}

		//查询：update video_comment set top_type=0 where video id=? and top_type=1
		VideoComment videoComment = new VideoComment();
		videoComment.setTopType(CommentTopTypeEnum.NO_TOP.getType());

		VideoCommentQuery videoCommentQuery = new VideoCommentQuery();
		videoCommentQuery.setVideoId(dbVideoComment.getVideoId());
		videoCommentQuery.setTopType(CommentTopTypeEnum.TOP.getType());
		videoCommentMapper.updateByParam(videoComment,videoCommentQuery);
	}

	@Override
	public void deleteComment(Integer commentId, String userId) {
		VideoComment comment = videoCommentMapper.selectByCommentId(commentId);
		if(comment==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(comment.getVideoId());
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		//视频发布者和评论发布者可以删除对应的评论
		if(!videoInfo.getUserId().equals(userId) && !comment.getUserId().equals(userId)){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		videoCommentMapper.deleteByCommentId(commentId);
		if(comment.getpCommentId()==0){
			videoInfoMapper.updateCountInfo(videoInfo.getVideoId(),UserActionTypeEnum.VIDEO_COMMENT.getField(), -1);
			//删除二级评论
			VideoCommentQuery videoCommentQuery = new VideoCommentQuery();
			videoCommentQuery.setpCommentId(commentId);
			videoCommentMapper.deleteByParam(videoCommentQuery);
		}
	}
}