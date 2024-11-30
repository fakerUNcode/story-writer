package com.easylive.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.UserVideoSeriesVideoQuery;
import com.easylive.entity.po.UserVideoSeriesVideo;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.UserVideoSeriesVideoMapper;
import com.easylive.service.UserVideoSeriesVideoService;
import com.easylive.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("userVideoSeriesVideoService")
public class UserVideoSeriesVideoServiceImpl implements UserVideoSeriesVideoService {

	@Resource
	private UserVideoSeriesVideoMapper<UserVideoSeriesVideo, UserVideoSeriesVideoQuery> userVideoSeriesVideoMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserVideoSeriesVideo> findListByParam(UserVideoSeriesVideoQuery param) {
		return this.userVideoSeriesVideoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserVideoSeriesVideoQuery param) {
		return this.userVideoSeriesVideoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserVideoSeriesVideo> findListByPage(UserVideoSeriesVideoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserVideoSeriesVideo> list = this.findListByParam(param);
		PaginationResultVO<UserVideoSeriesVideo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserVideoSeriesVideo bean) {
		return this.userVideoSeriesVideoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserVideoSeriesVideo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userVideoSeriesVideoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserVideoSeriesVideo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userVideoSeriesVideoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserVideoSeriesVideo bean, UserVideoSeriesVideoQuery param) {
		StringTools.checkParam(param);
		return this.userVideoSeriesVideoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserVideoSeriesVideoQuery param) {
		StringTools.checkParam(param);
		return this.userVideoSeriesVideoMapper.deleteByParam(param);
	}

	/**
	 * 根据SeriesIdAndVideoId获取对象
	 */
	@Override
	public UserVideoSeriesVideo getUserVideoSeriesVideoBySeriesIdAndVideoId(Integer seriesId, String videoId) {
		return this.userVideoSeriesVideoMapper.selectBySeriesIdAndVideoId(seriesId, videoId);
	}

	/**
	 * 根据SeriesIdAndVideoId修改
	 */
	@Override
	public Integer updateUserVideoSeriesVideoBySeriesIdAndVideoId(UserVideoSeriesVideo bean, Integer seriesId, String videoId) {
		return this.userVideoSeriesVideoMapper.updateBySeriesIdAndVideoId(bean, seriesId, videoId);
	}

	/**
	 * 根据SeriesIdAndVideoId删除
	 */
	@Override
	public Integer deleteUserVideoSeriesVideoBySeriesIdAndVideoId(Integer seriesId, String videoId) {
		return this.userVideoSeriesVideoMapper.deleteBySeriesIdAndVideoId(seriesId, videoId);
	}
}