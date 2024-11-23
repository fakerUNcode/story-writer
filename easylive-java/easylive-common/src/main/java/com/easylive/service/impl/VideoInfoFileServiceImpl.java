package com.easylive.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.VideoInfoFileMapper;
import com.easylive.service.VideoInfoFileService;
import com.easylive.utils.StringTools;


/**
 * 视频文件信息 业务接口实现
 */
@Service("videoInfoFileService")
public class VideoInfoFileServiceImpl implements VideoInfoFileService {

	@Resource
	private VideoInfoFileMapper<VideoInfoFile, VideoInfoFileQuery> videoInfoFileMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoInfoFile> findListByParam(VideoInfoFileQuery param) {
		return this.videoInfoFileMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoInfoFileQuery param) {
		return this.videoInfoFileMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoInfoFile> findListByPage(VideoInfoFileQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoInfoFile> list = this.findListByParam(param);
		PaginationResultVO<VideoInfoFile> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoInfoFile bean) {
		return this.videoInfoFileMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoInfoFile> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFileMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoInfoFile> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFileMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoInfoFile bean, VideoInfoFileQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoFileMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoInfoFileQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoFileMapper.deleteByParam(param);
	}

	/**
	 * 根据FileId获取对象
	 */
	@Override
	public VideoInfoFile getVideoInfoFileByFileId(String fileId) {
		return this.videoInfoFileMapper.selectByFileId(fileId);
	}

	/**
	 * 根据FileId修改
	 */
	@Override
	public Integer updateVideoInfoFileByFileId(VideoInfoFile bean, String fileId) {
		return this.videoInfoFileMapper.updateByFileId(bean, fileId);
	}

	/**
	 * 根据FileId删除
	 */
	@Override
	public Integer deleteVideoInfoFileByFileId(String fileId) {
		return this.videoInfoFileMapper.deleteByFileId(fileId);
	}
}