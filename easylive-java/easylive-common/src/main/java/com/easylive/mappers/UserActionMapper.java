package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 用户行为 点赞、评论 数据库操作接口
 */
public interface UserActionMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据ActionId更新
	 */
	 Integer updateByActionId(@Param("bean") T t,@Param("actionId") Integer actionId);


	/**
	 * 根据ActionId删除
	 */
	 Integer deleteByActionId(@Param("actionId") Integer actionId);


	/**
	 * 根据ActionId获取对象
	 */
	 T selectByActionId(@Param("actionId") Integer actionId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId更新
	 */
	 Integer updateByVideoIdAndCommentIdAndActionTypeAndUserId(@Param("bean") T t,@Param("videoId") String videoId,@Param("commentId") Integer commentId,@Param("actionType") Integer actionType,@Param("userId") String userId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId删除
	 */
	 Integer deleteByVideoIdAndCommentIdAndActionTypeAndUserId(@Param("videoId") String videoId,@Param("commentId") Integer commentId,@Param("actionType") Integer actionType,@Param("userId") String userId);


	/**
	 * 根据VideoIdAndCommentIdAndActionTypeAndUserId获取对象
	 */
	 T selectByVideoIdAndCommentIdAndActionTypeAndUserId(@Param("videoId") String videoId,@Param("commentId") Integer commentId,@Param("actionType") Integer actionType,@Param("userId") String userId);


}
