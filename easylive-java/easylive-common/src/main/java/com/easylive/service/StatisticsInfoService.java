package com.easylive.service;

import com.easylive.entity.po.StatisticsInfo;
import com.easylive.entity.query.StatisticsInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;

import java.util.List;


/**
 * 数据统计 业务接口
 */
public interface StatisticsInfoService {

	/**
	 * 根据条件查询列表
	 */
	List<StatisticsInfo> findListByParam(StatisticsInfoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(StatisticsInfoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<StatisticsInfo> findListByPage(StatisticsInfoQuery param);

	/**
	 * 新增
	 */
	Integer add(StatisticsInfo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<StatisticsInfo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<StatisticsInfo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(StatisticsInfo bean,StatisticsInfoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(StatisticsInfoQuery param);

	/**
	 * 根据StatisticsDateAndUserIdAndDataType查询对象
	 */
	StatisticsInfo getStatisticsInfoByStatisticsDateAndUserIdAndDataType(String statisticsDate,String userId,Integer dataType);


	/**
	 * 根据StatisticsDateAndUserIdAndDataType修改
	 */
	Integer updateStatisticsInfoByStatisticsDateAndUserIdAndDataType(StatisticsInfo bean,String statisticsDate,String userId,Integer dataType);


	/**
	 * 根据StatisticsDateAndUserIdAndDataType删除
	 */
	Integer deleteStatisticsInfoByStatisticsDateAndUserIdAndDataType(String statisticsDate,String userId,Integer dataType);

	void statisticsData();

}