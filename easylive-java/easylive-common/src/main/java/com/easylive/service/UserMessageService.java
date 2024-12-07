package com.easylive.service;

import com.easylive.entity.dto.UserMessageCountDto;
import com.easylive.entity.enums.MessageTypeEnum;
import com.easylive.entity.po.UserMessage;
import com.easylive.entity.query.UserMessageQuery;
import com.easylive.entity.vo.PaginationResultVO;

import java.util.List;


/**
 * 用户消息表 业务接口
 */
public interface UserMessageService {

	/**
	 * 根据条件查询列表
	 */
	List<UserMessage> findListByParam(UserMessageQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserMessageQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserMessage> findListByPage(UserMessageQuery param);

	/**
	 * 新增
	 */
	Integer add(UserMessage bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserMessage> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserMessage> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(UserMessage bean,UserMessageQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserMessageQuery param);

	/**
	 * 根据MessageId查询对象
	 */
	UserMessage getUserMessageByMessageId(Integer messageId);


	/**
	 * 根据MessageId修改
	 */
	Integer updateUserMessageByMessageId(UserMessage bean,Integer messageId);


	/**
	 * 根据MessageId删除
	 */
	Integer deleteUserMessageByMessageId(Integer messageId);

	void saveUserMessage(String videoId, String sendUserId, MessageTypeEnum messageTypeEnum, String content,Integer replyCommentId);

	List<UserMessageCountDto> getMessageTypeNoReadCount(String userId);
}