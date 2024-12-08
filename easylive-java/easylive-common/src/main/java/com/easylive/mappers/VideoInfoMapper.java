package com.easylive.mappers;

import com.easylive.entity.dto.CountInfoDto;
import org.apache.ibatis.annotations.Param;

/**
 * 视频信息 数据库操作接口
 */
public interface VideoInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据VideoId更新
	 */
	 Integer updateByVideoId(@Param("bean") T t,@Param("videoId") String videoId);


	/**
	 * 根据VideoId删除
	 */
	 Integer deleteByVideoId(@Param("videoId") String videoId);


	/**
	 * 根据VideoId获取对象
	 */
	 T selectByVideoId(@Param("videoId") String videoId);

	 void updateCountInfo(@Param("videoId")String videoId,@Param("field")String field,@Param("changeCount")Integer changeCount);

    CountInfoDto selectSumCountInfo(@Param("userId")String userId);
}
