package com.easylive.service;

import java.util.List;

import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.vo.PaginationResultVO;


/**
 * 视频文件信息 业务接口
 */
public interface VideoInfoFilePostService {

	/**
	 * 根据条件查询列表
	 */
	List<VideoInfoFilePost> findListByParam(VideoInfoFilePostQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(VideoInfoFilePostQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<VideoInfoFilePost> findListByPage(VideoInfoFilePostQuery param);

	/**
	 * 新增
	 */
	Integer add(VideoInfoFilePost bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<VideoInfoFilePost> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<VideoInfoFilePost> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(VideoInfoFilePost bean,VideoInfoFilePostQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(VideoInfoFilePostQuery param);

	/**
	 * 根据FileId查询对象
	 */
	VideoInfoFilePost getVideoInfoFilePostByFileId(String fileId);


	/**
	 * 根据FileId修改
	 */
	Integer updateVideoInfoFilePostByFileId(VideoInfoFilePost bean,String fileId);


	/**
	 * 根据FileId删除
	 */
	Integer deleteVideoInfoFilePostByFileId(String fileId);


	/**
	 * 根据UploadIdAndUserId查询对象
	 */
	VideoInfoFilePost getVideoInfoFilePostByUploadIdAndUserId(String uploadId,String userId);


	/**
	 * 根据UploadIdAndUserId修改
	 */
	Integer updateVideoInfoFilePostByUploadIdAndUserId(VideoInfoFilePost bean,String uploadId,String userId);


	/**
	 * 根据UploadIdAndUserId删除
	 */
	Integer deleteVideoInfoFilePostByUploadIdAndUserId(String uploadId,String userId);

}