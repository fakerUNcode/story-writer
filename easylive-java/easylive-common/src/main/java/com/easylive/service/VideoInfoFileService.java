package com.easylive.service;

import java.util.List;

import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.vo.PaginationResultVO;


/**
 * 视频文件信息 业务接口
 */
public interface VideoInfoFileService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfoFile> findListByParam(VideoInfoFileQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(VideoInfoFileQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfoFile> findListByPage(VideoInfoFileQuery param);

	/**
	 * 新增
	 */
	Integer add(VideoInfoFile bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<VideoInfoFile> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<VideoInfoFile> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(VideoInfoFile bean,VideoInfoFileQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(VideoInfoFileQuery param);

	/**
	 * 根据FileId查询对象
	 */
	VideoInfoFile getVideoInfoFileByFileId(String fileId);


	/**
	 * 根据FileId修改
	 */
	Integer updateVideoInfoFileByFileId(VideoInfoFile bean,String fileId);


	/**
	 * 根据FileId删除
	 */
	Integer deleteVideoInfoFileByFileId(String fileId);

}