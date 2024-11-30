package com.easylive.service;

import java.util.List;

import com.easylive.entity.query.UserVideoSeriesVideoQuery;
import com.easylive.entity.po.UserVideoSeriesVideo;
import com.easylive.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface UserVideoSeriesVideoService {

	/**
	 * 根据条件查询列表
	 */
	List<UserVideoSeriesVideo> findListByParam(UserVideoSeriesVideoQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserVideoSeriesVideoQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserVideoSeriesVideo> findListByPage(UserVideoSeriesVideoQuery param);

	/**
	 * 新增
	 */
	Integer add(UserVideoSeriesVideo bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserVideoSeriesVideo> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserVideoSeriesVideo> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(UserVideoSeriesVideo bean,UserVideoSeriesVideoQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserVideoSeriesVideoQuery param);

	/**
	 * 根据SeriesIdAndVideoId查询对象
	 */
	UserVideoSeriesVideo getUserVideoSeriesVideoBySeriesIdAndVideoId(Integer seriesId,String videoId);


	/**
	 * 根据SeriesIdAndVideoId修改
	 */
	Integer updateUserVideoSeriesVideoBySeriesIdAndVideoId(UserVideoSeriesVideo bean,Integer seriesId,String videoId);


	/**
	 * 根据SeriesIdAndVideoId删除
	 */
	Integer deleteUserVideoSeriesVideoBySeriesIdAndVideoId(Integer seriesId,String videoId);

}