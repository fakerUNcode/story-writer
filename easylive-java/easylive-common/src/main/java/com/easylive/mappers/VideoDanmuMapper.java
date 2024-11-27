package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 视频弹幕 数据库操作接口
 */
public interface VideoDanmuMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据DanmuId更新
	 */
	 Integer updateByDanmuId(@Param("bean") T t,@Param("danmuId") Integer danmuId);


	/**
	 * 根据DanmuId删除
	 */
	 Integer deleteByDanmuId(@Param("danmuId") Integer danmuId);


	/**
	 * 根据DanmuId获取对象
	 */
	 T selectByDanmuId(@Param("danmuId") Integer danmuId);


}
