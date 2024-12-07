package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 视频播放历史 数据库操作接口
 */
public interface VideoPlayHistoryMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据UserIdAndVideoId更新
	 */
	 Integer updateByUserIdAndVideoId(@Param("bean") T t,@Param("userId") String userId,@Param("videoId") String videoId);


	/**
	 * 根据UserIdAndVideoId删除
	 */
	 Integer deleteByUserIdAndVideoId(@Param("userId") String userId,@Param("videoId") String videoId);


	/**
	 * 根据UserIdAndVideoId获取对象
	 */
	 T selectByUserIdAndVideoId(@Param("userId") String userId,@Param("videoId") String videoId);


}
