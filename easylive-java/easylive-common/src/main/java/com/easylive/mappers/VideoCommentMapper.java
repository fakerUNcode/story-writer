package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论 数据库操作接口
 */
public interface VideoCommentMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据CommentId更新
	 */
	 Integer updateByCommentId(@Param("bean") T t,@Param("commentId") Integer commentId);


	/**
	 * 根据CommentId删除
	 */
	 Integer deleteByCommentId(@Param("commentId") Integer commentId);


	/**
	 * 根据CommentId获取对象
	 */
	 T selectByCommentId(@Param("commentId") Integer commentId);


    List<T> selectListWithChildren(@Param("query")P p);
}
