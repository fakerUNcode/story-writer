package com.easylive.mappers;

import com.easylive.entity.po.VideoInfoFilePost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 视频文件信息 数据库操作接口
 */
public interface VideoInfoFilePostMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据FileId更新
	 */
	 Integer updateByFileId(@Param("bean") T t,@Param("fileId") String fileId);


	/**
	 * 根据FileId删除
	 */
	 Integer deleteByFileId(@Param("fileId") String fileId);


	/**
	 * 根据FileId获取对象
	 */
	 T selectByFileId(@Param("fileId") String fileId);


	/**
	 * 根据UploadIdAndUserId更新
	 */
	 Integer updateByUploadIdAndUserId(@Param("bean") T t,@Param("uploadId") String uploadId,@Param("userId") String userId);


	/**
	 * 根据UploadIdAndUserId删除
	 */
	 Integer deleteByUploadIdAndUserId(@Param("uploadId") String uploadId,@Param("userId") String userId);


	/**
	 * 根据UploadIdAndUserId获取对象
	 */
	 T selectByUploadIdAndUserId(@Param("uploadId") String uploadId,@Param("userId") String userId);

	 void deleteBatchByFileId(@Param("fileIdList")List<String> fileIdList, @Param("userId")String userId);

    Integer sumDuration(@Param("videoId") String videoId);
	int update(@Param("filePost") VideoInfoFilePost videoInfoFilePost);
}
