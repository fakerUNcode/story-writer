package com.easylive.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.VideoPlayHistoryQuery;
import com.easylive.entity.po.VideoPlayHistory;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.VideoPlayHistoryMapper;
import com.easylive.service.VideoPlayHistoryService;
import com.easylive.utils.StringTools;


/**
 * 视频播放历史 业务接口实现
 */
@Service("videoPlayHistoryService")
public class VideoPlayHistoryServiceImpl implements VideoPlayHistoryService {

	@Resource
	private VideoPlayHistoryMapper<VideoPlayHistory, VideoPlayHistoryQuery> videoPlayHistoryMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoPlayHistory> findListByParam(VideoPlayHistoryQuery param) {
		return this.videoPlayHistoryMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoPlayHistoryQuery param) {
		return this.videoPlayHistoryMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoPlayHistory> findListByPage(VideoPlayHistoryQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoPlayHistory> list = this.findListByParam(param);
		PaginationResultVO<VideoPlayHistory> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoPlayHistory bean) {
		return this.videoPlayHistoryMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoPlayHistory> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoPlayHistoryMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoPlayHistory> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoPlayHistoryMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoPlayHistory bean, VideoPlayHistoryQuery param) {
		StringTools.checkParam(param);
		return this.videoPlayHistoryMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoPlayHistoryQuery param) {
		StringTools.checkParam(param);
		return this.videoPlayHistoryMapper.deleteByParam(param);
	}

	/**
	 * 根据UserIdAndVideoId获取对象
	 */
	@Override
	public VideoPlayHistory getVideoPlayHistoryByUserIdAndVideoId(String userId, String videoId) {
		return this.videoPlayHistoryMapper.selectByUserIdAndVideoId(userId, videoId);
	}

	/**
	 * 根据UserIdAndVideoId修改
	 */
	@Override
	public Integer updateVideoPlayHistoryByUserIdAndVideoId(VideoPlayHistory bean, String userId, String videoId) {
		return this.videoPlayHistoryMapper.updateByUserIdAndVideoId(bean, userId, videoId);
	}

	/**
	 * 根据UserIdAndVideoId删除
	 */
	@Override
	public Integer deleteVideoPlayHistoryByUserIdAndVideoId(String userId, String videoId) {
		return this.videoPlayHistoryMapper.deleteByUserIdAndVideoId(userId, videoId);
	}
}