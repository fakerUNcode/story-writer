package com.easylive.service;

import com.easylive.entity.po.UserAction;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.vo.PaginationResultVO;

import java.util.List;


/**
 * 用户行为 点赞、评论 业务接口
 */
public interface UserActionService {

	/**
	 * 根据条件查询列表
	 */
	List<UserAction> findListByParam(UserActionQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserActionQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserAction> findListByPage(UserActionQuery param);

	/**
	 * 新增
	 */
	Integer add(UserAction bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserAction> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserAction> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(UserAction bean,UserActionQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserActionQuery param);

	/**
	 * 根据ActionId查询对象
	 */
	UserAction getUserActionByActionId(Integer actionId);


	/**
	 * 根据ActionId修改
	 */
	Integer updateUserActionByActionId(UserAction bean,Integer actionId);


	/**
	 * 根据ActionId删除
	 */
	Integer deleteUserActionByActionId(Integer actionId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId查询对象
	 */
	UserAction getUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(String videoId,Integer commentId,Integer actionType,String userId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId修改
	 */
	Integer updateUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(UserAction bean,String videoId,Integer commentId,Integer actionType,String userId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId删除
	 */
	Integer deleteUserActionByVideoIdAndCommentIdAndActionTypeAndUserId(String videoId,Integer commentId,Integer actionType,String userId);

    void saveAction(UserAction userAction);
}