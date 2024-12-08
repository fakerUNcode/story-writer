package com.easylive.service;

import com.easylive.entity.po.VideoPlayHistory;
import com.easylive.entity.query.VideoPlayHistoryQuery;
import com.easylive.entity.vo.PaginationResultVO;

import java.util.List;


/**
 * 视频播放历史 业务接口
 */
public interface VideoPlayHistoryService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoPlayHistory> findListByParam(VideoPlayHistoryQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(VideoPlayHistoryQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoPlayHistory> findListByPage(VideoPlayHistoryQuery param);

	/**
	 * 新增
	 */
	Integer add(VideoPlayHistory bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<VideoPlayHistory> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<VideoPlayHistory> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(VideoPlayHistory bean,VideoPlayHistoryQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(VideoPlayHistoryQuery param);

	/**
	 * 根据UserIdAndVideoId查询对象
	 */
	VideoPlayHistory getVideoPlayHistoryByUserIdAndVideoId(String userId,String videoId);


	/**
	 * 根据UserIdAndVideoId修改
	 */
	Integer updateVideoPlayHistoryByUserIdAndVideoId(VideoPlayHistory bean,String userId,String videoId);


	/**
	 * 根据UserIdAndVideoId删除
	 */
	Integer deleteVideoPlayHistoryByUserIdAndVideoId(String userId,String videoId);

	void saveHistory(String userId,String videoId,Integer fileIndex);

}