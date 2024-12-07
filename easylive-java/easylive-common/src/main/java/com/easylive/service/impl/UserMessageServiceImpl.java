package com.easylive.service.impl;

import com.easylive.entity.dto.UserMessageExtendDto;
import com.easylive.entity.enums.MessageReadTypeEnum;
import com.easylive.entity.enums.MessageTypeEnum;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.po.UserMessage;
import com.easylive.entity.po.VideoComment;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.*;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.mappers.UserMessageMapper;
import com.easylive.mappers.VideoCommentMapper;
import com.easylive.mappers.VideoInfoMapper;
import com.easylive.mappers.VideoInfoPostMapper;
import com.easylive.service.UserMessageService;
import com.easylive.utils.JsonUtils;
import com.easylive.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 用户消息表 业务接口实现
 */
@Service("userMessageService")
public class UserMessageServiceImpl implements UserMessageService {

	@Resource
	private UserMessageMapper<UserMessage, UserMessageQuery> userMessageMapper;
	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private VideoCommentMapper<VideoComment, VideoCommentQuery> videoCommentMapper;
	@Resource
	private VideoInfoPostMapper<VideoInfoPost, VideoInfoPostQuery> videoInfoPostMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserMessage> findListByParam(UserMessageQuery param) {
		return this.userMessageMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserMessageQuery param) {
		return this.userMessageMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserMessage> findListByPage(UserMessageQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserMessage> list = this.findListByParam(param);
		PaginationResultVO<UserMessage> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserMessage bean) {
		return this.userMessageMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserMessage> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userMessageMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserMessage> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userMessageMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserMessage bean, UserMessageQuery param) {
		StringTools.checkParam(param);
		return this.userMessageMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserMessageQuery param) {
		StringTools.checkParam(param);
		return this.userMessageMapper.deleteByParam(param);
	}

	/**
	 * 根据MessageId获取对象
	 */
	@Override
	public UserMessage getUserMessageByMessageId(Integer messageId) {
		return this.userMessageMapper.selectByMessageId(messageId);
	}

	/**
	 * 根据MessageId修改
	 */
	@Override
	public Integer updateUserMessageByMessageId(UserMessage bean, Integer messageId) {
		return this.userMessageMapper.updateByMessageId(bean, messageId);
	}

	/**
	 * 根据MessageId删除
	 */
	@Override
	public Integer deleteUserMessageByMessageId(Integer messageId) {
		return this.userMessageMapper.deleteByMessageId(messageId);
	}


	//保存用户消息方法
	/*使用@Async注解,让消息保存操作在独立线程中执行,这样就不会阻塞主程序流程,提高了系统的响应性能。*/
	@Override
	@Async
	public void saveUserMessage(String videoId, String sendUserId, MessageTypeEnum messageTypeEnum, String content, Integer replyCommentId) {
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(videoId);
		if(videoInfo==null){
			return;
		}
		UserMessageExtendDto extendDto = new UserMessageExtendDto();
		extendDto.setMessageContent(content);

		String userId = videoInfo.getUserId();

		//专门处理点赞和收藏这类可以"切换"的操作，如果用户反复点赞取消点赞,我们不希望接收者收到无穷无尽的通知,这会造成很糟糕的用户体验
		if (ArrayUtils.contains(
				new Integer[]{
						MessageTypeEnum.LIKE.getType(),
						MessageTypeEnum.COLLECTION.getType()
				},
				messageTypeEnum
		)) {
			// 如果消息类型是点赞或收藏，执行相应操作
			UserMessageQuery userMessageQuery = new UserMessageQuery();
			userMessageQuery.setUserId(userId);
			userMessageQuery.setVideoId(videoId);
			userMessageQuery.setMessageType(messageTypeEnum.getType());
			Integer count = userMessageMapper.selectCount(userMessageQuery);
			//已经发送过的消息不再发送
			if(count>0){
				return;
			}
		}
		UserMessage userMessage = new UserMessage();
		userMessage.setUserId(userId);                                     // 设置用户ID
		userMessage.setVideoId(videoId);                                   // 设置视频ID
		userMessage.setReadType(MessageReadTypeEnum.NO_READ.getType());    // 设置消息阅读状态为未读
		userMessage.setCreateTime(new Date());                             // 设置消息创建时间为当前时间
		userMessage.setMessageType(messageTypeEnum.getType());             // 设置消息类型
		userMessage.setSendUserId(sendUserId);                             // 设置发送者用户ID
		//评论回复的特殊处理，确保消息送达正确的接收者(原评论者)
		if(replyCommentId!=null){
			VideoComment replyComment = videoCommentMapper.selectByCommentId(replyCommentId);
			if(replyComment!=null){
				userId = replyComment.getUserId();
				extendDto.setMessageContentReply(replyComment.getContent());
			}
			if(userId.equals(sendUserId)){
				return;
			}
		}
		//系统消息特殊处理
		if(MessageTypeEnum.SYS==messageTypeEnum){
			VideoInfoPost videoInfoPost = videoInfoPostMapper.selectByVideoId(videoId);
			extendDto.setAuditStatus(videoInfoPost.getStatus());
		}
		userMessage.setUserId(userId);
		userMessage.setExtendJson(JsonUtils.convertObj2Json(extendDto));
		this.userMessageMapper.insert(userMessage);
	}
}