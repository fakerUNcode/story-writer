package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *  数据库操作接口
 */
public interface UserVideoSeriesVideoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据SeriesIdAndVideoId更新
	 */
	 Integer updateBySeriesIdAndVideoId(@Param("bean") T t,@Param("seriesId") Integer seriesId,@Param("videoId") String videoId);


	/**
	 * 根据SeriesIdAndVideoId删除
	 */
	 Integer deleteBySeriesIdAndVideoId(@Param("seriesId") Integer seriesId,@Param("videoId") String videoId);


	/**
	 * 根据SeriesIdAndVideoId获取对象
	 */
	 T selectBySeriesIdAndVideoId(@Param("seriesId") Integer seriesId,@Param("videoId") String videoId);


}
