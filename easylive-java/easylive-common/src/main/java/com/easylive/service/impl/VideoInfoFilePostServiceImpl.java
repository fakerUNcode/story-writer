package com.easylive.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easylive.entity.enums.PageSize;
import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.query.SimplePage;
import com.easylive.mappers.VideoInfoFilePostMapper;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.utils.StringTools;


/**
 * 视频文件信息 业务接口实现
 */
@Service("videoInfoFilePostService")
public class VideoInfoFilePostServiceImpl implements VideoInfoFilePostService {

	@Resource
	private VideoInfoFilePostMapper<VideoInfoFilePost, VideoInfoFilePostQuery> videoInfoFilePostMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoInfoFilePost> findListByParam(VideoInfoFilePostQuery param) {
		return this.videoInfoFilePostMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoInfoFilePostQuery param) {
		return this.videoInfoFilePostMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoInfoFilePost> findListByPage(VideoInfoFilePostQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoInfoFilePost> list = this.findListByParam(param);
		PaginationResultVO<VideoInfoFilePost> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoInfoFilePost bean) {
		return this.videoInfoFilePostMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoInfoFilePost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFilePostMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoInfoFilePost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoFilePostMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoInfoFilePost bean, VideoInfoFilePostQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoFilePostMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoInfoFilePostQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoFilePostMapper.deleteByParam(param);
	}

	/**
	 * 根据FileId获取对象
	 */
	@Override
	public VideoInfoFilePost getVideoInfoFilePostByFileId(String fileId) {
		return this.videoInfoFilePostMapper.selectByFileId(fileId);
	}

	/**
	 * 根据FileId修改
	 */
	@Override
	public Integer updateVideoInfoFilePostByFileId(VideoInfoFilePost bean, String fileId) {
		return this.videoInfoFilePostMapper.updateByFileId(bean, fileId);
	}

	/**
	 * 根据FileId删除
	 */
	@Override
	public Integer deleteVideoInfoFilePostByFileId(String fileId) {
		return this.videoInfoFilePostMapper.deleteByFileId(fileId);
	}

	/**
	 * 根据UploadIdAndUserId获取对象
	 */
	@Override
	public VideoInfoFilePost getVideoInfoFilePostByUploadIdAndUserId(String uploadId, String userId) {
		return this.videoInfoFilePostMapper.selectByUploadIdAndUserId(uploadId, userId);
	}

	/**
	 * 根据UploadIdAndUserId修改
	 */
	@Override
	public Integer updateVideoInfoFilePostByUploadIdAndUserId(VideoInfoFilePost bean, String uploadId, String userId) {
		return this.videoInfoFilePostMapper.updateByUploadIdAndUserId(bean, uploadId, userId);
	}

	/**
	 * 根据UploadIdAndUserId删除
	 */
	@Override
	public Integer deleteVideoInfoFilePostByUploadIdAndUserId(String uploadId, String userId) {
		return this.videoInfoFilePostMapper.deleteByUploadIdAndUserId(uploadId, userId);
	}
}