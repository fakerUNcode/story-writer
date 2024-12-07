package com.easylive.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 数据统计 数据库操作接口
 */
public interface StatisticsInfoMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据StatisticsDateAndUserIdAndDataType更新
	 */
	 Integer updateByStatisticsDateAndUserIdAndDataType(@Param("bean") T t,@Param("statisticsDate") String statisticsDate,@Param("userId") String userId,@Param("dataType") Integer dataType);


	/**
	 * 根据StatisticsDateAndUserIdAndDataType删除
	 */
	 Integer deleteByStatisticsDateAndUserIdAndDataType(@Param("statisticsDate") String statisticsDate,@Param("userId") String userId,@Param("dataType") Integer dataType);


	/**
	 * 根据StatisticsDateAndUserIdAndDataType获取对象
	 */
	 T selectByStatisticsDateAndUserIdAndDataType(@Param("statisticsDate") String statisticsDate,@Param("userId") String userId,@Param("dataType") Integer dataType);


}
