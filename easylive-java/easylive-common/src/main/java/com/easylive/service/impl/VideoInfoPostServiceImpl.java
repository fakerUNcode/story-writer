package com.easylive.service.impl;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.enums.*;
import com.easylive.entity.po.*;
import com.easylive.entity.query.*;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.*;
import com.easylive.service.VideoInfoPostService;
import com.easylive.utils.CopyTools;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 视频信息 业务接口实现
 */

@Slf4j
@Service("videoInfoPostService")
public class VideoInfoPostServiceImpl implements VideoInfoPostService {
	@Resource
	private RedisComponent redisComponent;
	@Resource
	private FFmpegUtils fFmpegUtils;

	@Resource
	private VideoInfoPostMapper<VideoInfoPost, VideoInfoPostQuery> videoInfoPostMapper;
	@Resource
	private VideoInfoFilePostMapper <VideoInfoFilePost,VideoInfoFilePostQuery> videoInfoFilePostMapper;

	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private VideoInfoFileMapper<VideoInfoFile, VideoInfoFileQuery> videoInfoFileMapper;
	@Resource
	private AppConfig appConfig;

	@Resource
	private EsSearchComponent esSearchComponent;
	@Resource
	private UserInfoMapper<UserInfo,UserInfoQuery> userInfoMapper;


	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoInfoPost> findListByParam(VideoInfoPostQuery param) {
		return this.videoInfoPostMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoInfoPostQuery param) {
		return this.videoInfoPostMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoInfoPost> findListByPage(VideoInfoPostQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoInfoPost> list = this.findListByParam(param);
		PaginationResultVO<VideoInfoPost> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoInfoPost bean) {
		return this.videoInfoPostMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoInfoPost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoPostMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoInfoPost> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoPostMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoInfoPost bean, VideoInfoPostQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoPostMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoInfoPostQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoPostMapper.deleteByParam(param);
	}

	/**
	 * 根据VideoId获取对象
	 */
	@Override
	public VideoInfoPost getVideoInfoPostByVideoId(String videoId) {
		return this.videoInfoPostMapper.selectByVideoId(videoId);
	}

	/**
	 * 根据VideoId修改
	 */
	@Override
	public Integer updateVideoInfoPostByVideoId(VideoInfoPost bean, String videoId) {
		return this.videoInfoPostMapper.updateByVideoId(bean, videoId);
	}

	/**
	 * 根据VideoId删除
	 */
	@Override
	public Integer deleteVideoInfoPostByVideoId(String videoId) {
		return this.videoInfoPostMapper.deleteByVideoId(videoId);
	}

	//视频信息保存方法
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveVideoInfo(VideoInfoPost videoInfoPost, List<VideoInfoFilePost> uploadFileList) {
		//上传文件数量不允许超过了系统配置中允许的最大视频文件数量
		if(uploadFileList.size() > redisComponent.getSysSettingDto().getVideoPCount()){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		if(!StringTools.isEmpty(videoInfoPost.getVideoId())){
			VideoInfoPost videoInfoPostDb = this.videoInfoPostMapper.selectByVideoId(videoInfoPost.getVideoId());
			if(videoInfoPostDb==null){
				throw new BusinessException(ResponseCodeEnum.CODE_600);
			}
			//状态为为 STATUS0（转码中）或 STATUS2（待审核）的视频不允许作更改
			if(ArrayUtils.contains(new Integer[]{VideoStatusEnum.STATUS0.getStatus(), VideoStatusEnum.STATUS2.getStatus()}, videoInfoPostDb.getStatus())){
				throw new BusinessException(ResponseCodeEnum.CODE_600);
			}
		}
		Date curDate = new Date();
		String videoId = videoInfoPost.getVideoId();
		//用户可能多次上传分p,我们关心被删除的和被新加的
		List<VideoInfoFilePost> deleteFileList = new ArrayList<>();
		//默认情况下，addFileList 包含所有上传的文件，因此初始化为uploadFileList。即使没有新增文件需要插入也需要按照逻辑处理
		List<VideoInfoFilePost> addFileList = uploadFileList;
		//无id说明第一次上传，直接上传即可
		if(StringTools.isEmpty(videoId)){
			videoId = StringTools.getRandomString(Constants.LENGTH_10);
			videoInfoPost.setVideoId(videoId);
			videoInfoPost.setCreateTime(curDate);
			videoInfoPost.setLastUpdateTime(curDate);
			videoInfoPost.setStatus(VideoStatusEnum.STATUS0.getStatus());
			this.videoInfoPostMapper.insert(videoInfoPost);
		}else {
			VideoInfoFilePostQuery filePostQuery = new VideoInfoFilePostQuery();
			filePostQuery.setVideoId(videoId);
			//除了videoId外，userId也是重要的参数：限定userId可以防止用户篡改别人的视频，用户在修改视频时必须传入用户自己的id才能修改
			filePostQuery.setUserId(videoInfoPost.getUserId());
			List<VideoInfoFilePost> dbInfoFileList = this.videoInfoFilePostMapper.selectList(filePostQuery);
			/* 将 uploadFileList 中的每个 VideoInfoFilePost 对象转化为一个 Map，其中：
			键（key）：uploadId（每个文件的唯一标识符）。
			值（value）：VideoInfoFilePost 对象本身。
			如果列表中有多个对象具有相同的 uploadId，则只保留最后一个（data2 会覆盖 data1）
			这样的覆盖就会成功让新的文件覆盖掉需要修改的文件 */
			Map<String,VideoInfoFilePost> uploadFileMap = uploadFileList.stream().collect(Collectors.toMap(item->item.getUploadId(), Function.identity(),
					(data1,data2)->data2));
			Boolean updateFileName = false;
			for(VideoInfoFilePost videoInfoFilePost:dbInfoFileList){
				VideoInfoFilePost updateFile = uploadFileMap.get(videoInfoFilePost.getUploadId());
				//如果数据库中查询不到上传id，说明该视频已被删除，应该加入删除列表
				if(updateFile==null){
					deleteFileList.add(videoInfoFilePost);
				}else if(!updateFile.getFileName().equals(videoInfoFilePost.getFileName())){
					//文件名被修改则标记
					updateFileName = true;
				}
			}

			addFileList = uploadFileList.stream().filter(item->item.getFileId() == null).collect(Collectors.toList());
			videoInfoPost.setLastUpdateTime(curDate);

			Boolean changeVideoInfo = this.changeVideoInfo(videoInfoPost);
			//如果用户只删除而没有添加新的文件则不需要打上标记重新审核
			if(addFileList!=null && !addFileList.isEmpty()){
				videoInfoPost.setStatus(VideoStatusEnum.STATUS0.getStatus());
			}else if(changeVideoInfo || updateFileName){
				//若用户更改了视频信息或修改的视频分p中任一文件的名字 则需要重新打回审核
				videoInfoPost.setStatus(VideoStatusEnum.STATUS2.getStatus());
			}
			this.videoInfoPostMapper.updateByVideoId(videoInfoPost, videoInfoPost.getVideoId());
		}
		//deleteFileList不为空，说明有文件需要处理
		if(!deleteFileList.isEmpty()){
			//使用 stream 遍历 deleteFileList，提取每个 VideoInfoFilePost 对象的 fileId。
			//将这些 fileId 收集到一个列表 delFileList 并调用数据库删除
			List<String> delFileList = deleteFileList.stream().map(item->item.getFileId()).collect(Collectors.toList());
			this.videoInfoFilePostMapper.deleteBatchByFileId(delFileList,videoInfoPost.getUserId());
			//提取文件路径并加入 Redis 删除队列
			List<String> delFilePathList = deleteFileList.stream().map(item->item.getFilePath()).collect(Collectors.toList());
			redisComponent.addFile2DelQueue(videoId,delFilePathList);
		}

		Integer index = 1;
		for(VideoInfoFilePost videoInfoFilePost : uploadFileList){
			videoInfoFilePost.setFileIndex(index++);
			videoInfoFilePost.setVideoId(videoId);
			videoInfoFilePost.setUserId(videoInfoPost.getUserId());
			//如果是新文件则分配信息和状态
			if(videoInfoFilePost.getFileId()==null){
				videoInfoFilePost.setFileId(StringTools.getRandomString(Constants.LENGTH_20));
				videoInfoFilePost.setUpdateType(VideoFileUpdateTypeEnum.UPDATE.getStatus());
				videoInfoFilePost.setTransferResult(VideoFileTransferResultEnum.TRANSFER.getStatus());
			}
		}
		//对上传文件进行批量插入或更新，并将新增文件加入到 Redis 转码队列中
		this.videoInfoFilePostMapper.insertOrUpdateBatch(uploadFileList);
		if(addFileList != null && !addFileList.isEmpty()){
			for(VideoInfoFilePost file : addFileList){
				file.setUserId(videoInfoPost.getUserId());
				file.setVideoId(videoId);
			}
			redisComponent.addFile2TransferQueue(addFileList);
		}
	}

	private Boolean changeVideoInfo(VideoInfoPost videoInfoPost){
		VideoInfoPost dbInfo = this.videoInfoPostMapper.selectByVideoId((videoInfoPost.getVideoId()));
		//检测标题、封面、标签、简介等信息是否更改
		if(!videoInfoPost.getVideoCover().equals(dbInfo.getVideoCover()) ||
				!videoInfoPost.getVideoName().equals(dbInfo.getVideoName()) ||
				!videoInfoPost.getTags().equals(dbInfo.getTags()) ||
				!videoInfoPost.getIntroduction().equals(dbInfo.getIntroduction()==null?"":dbInfo.getIntroduction())){
			return true;
		}
		return false;
	}

	//将视频从临时目录迁移到正式目录的方法
	@Override
	public void transferVideoFile(VideoInfoFilePost videoInfoFilePost) {
		VideoInfoFilePost updateFilePost = new VideoInfoFilePost();
		try{
			UploadingFileDto fileDto = redisComponent.getUploadVideoFile(videoInfoFilePost.getUserId(),videoInfoFilePost.getUploadId());
			String tempFilePath = appConfig.getProjectFolder()+Constants.FILE_FOLDER+Constants.FILE_FOLDER_TEMP+fileDto.getFilePath();
			File tempFile = new File(tempFilePath);
			//把temp目录中的视频迁移到正式目录/video中
			String targetFilePath = appConfig.getProjectFolder()+Constants.FILE_FOLDER+Constants.FILE_VIDEO+fileDto.getFilePath();
			File targetFile = new File(targetFilePath);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			FileUtils.copyDirectory(tempFile,targetFile);
			//删除掉临时目录
			FileUtils.forceDelete(tempFile);
			//清除消息队列中的临时视频信息
			redisComponent.delVideoFileInfo(videoInfoFilePost.getUserId(),videoInfoFilePost.getUploadId());
			//把视频分块合并为视频
			String completeVideo = targetFilePath + Constants.TEMP_VIDEO_NAME;
			this.union(targetFilePath,completeVideo,true);

			//获取播放时长
			Integer duration = fFmpegUtils.getVideoInfoDuration(completeVideo);
			updateFilePost.setDuration(duration);
			updateFilePost.setFileSize(new File(completeVideo).length());
			updateFilePost.setFilePath(Constants.FILE_VIDEO+fileDto.getFilePath());
			updateFilePost.setTransferResult(VideoFileTransferResultEnum.SUCCESS.getStatus());

			this.convertVideo2Ts(completeVideo);
		}catch (Exception e){
			log.error("文件转码失败",e);
			updateFilePost.setTransferResult(VideoFileTransferResultEnum.FAIL.getStatus());
		}finally {
			videoInfoFilePostMapper.updateByUploadIdAndUserId(updateFilePost,videoInfoFilePost.getUploadId(),videoInfoFilePost.getUserId());
			VideoInfoFilePostQuery filePostQuery = new VideoInfoFilePostQuery();
			filePostQuery.setVideoId(videoInfoFilePost.getVideoId());
			filePostQuery.setTransferResult(VideoFileTransferResultEnum.FAIL.getStatus());
			//如果有转码失败的则更新信息
			Integer failCount = videoInfoFilePostMapper.selectCount(filePostQuery);
			if(failCount>0){
				VideoInfoPost videoUpdate = new VideoInfoPost();
				videoUpdate.setStatus(VideoStatusEnum.STATUS1.getStatus());
				videoInfoPostMapper.updateByVideoId(videoUpdate,videoInfoFilePost.getVideoId());
				return;
			}
			//文件全部转码完毕则更新信息
			filePostQuery.setTransferResult(VideoFileTransferResultEnum.TRANSFER.getStatus());
			Integer transferCount = videoInfoFilePostMapper.selectCount(filePostQuery);
			if(transferCount==0){
				//此处计算所有分p的时长总和，这个总和是用户浏览视频前看到的时长预览
				Integer duration = videoInfoFilePostMapper.sumDuration(videoInfoFilePost.getVideoId());
				VideoInfoPost videoUpdate = new VideoInfoPost();
				//全部文件转码完成后进入待审核状态
				videoUpdate.setStatus(VideoStatusEnum.STATUS2.getStatus());
				videoUpdate.setDuration(duration);
				videoInfoPostMapper.updateByVideoId(videoUpdate,videoInfoFilePost.getVideoId());

			}
		}
	}

	//视频转Ts方法
	private void convertVideo2Ts(String completeVideo){
		File videoFile = new File(completeVideo);
		File tsFolder = videoFile.getParentFile();
		//视频编码转为h264
		String codec = fFmpegUtils.getVideoCodec(completeVideo);
		//若是hevc格式则需要转码
		if(Constants.VIDEO_CODE_HEVC.equals(codec)){
			//把文件名改为加上临时转码后缀
			String tempFileName = completeVideo + Constants.VIDEO_CODE_TEMP_FILE_SUFFIX;
			new File(completeVideo).renameTo(new File(tempFileName));
			fFmpegUtils.convertHevc2Mp4(tempFileName,completeVideo);
			new File(tempFileName).delete();
		}
		fFmpegUtils.convertVideo2Ts(tsFolder,completeVideo);
		//转码完成后删除临时文件
		videoFile.delete();
	}

	//文件合并辅助方法
	private void union(String dirPath, String toFilePath, Boolean delSource) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			throw new BusinessException("目录不存在");
		}
		File fileList[] = dir.listFiles();
		File targetFile = new File(toFilePath);
		try (RandomAccessFile writeFile = new RandomAccessFile(targetFile, "rw")) {
			byte[] b = new byte[1024 * 10];
			for (int i = 0; i < fileList.length; i++) {
				int len = -1;
				// 创建读取块文件的对象
				File chunkFile = new File(dirPath + File.separator + i);
				RandomAccessFile readFile = null;
				try {
					readFile = new RandomAccessFile(chunkFile, "r");
					while ((len = readFile.read(b)) != -1) {
						writeFile.write(b, 0, len);
					}
				} catch (Exception e) {
					log.error("合并分片失败", e);
					throw new BusinessException("合并文件失败");
				} finally {
					readFile.close();
				}
			}
		} catch (Exception e) {
			throw new BusinessException("合并文件" + dirPath + "出错了");
		} finally {
			if (delSource) {
				for (int i = 0; i < fileList.length; i++) {
					fileList[i].delete();
				}
			}
		}
	}

	//审核视频方法
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void auditVideo(String videoId, Integer status, String reason) {
		VideoStatusEnum videoStatusEnum = VideoStatusEnum.getByStatus(status);
		if (videoStatusEnum == null) {
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}

		VideoInfoPost videoInfoPost = new VideoInfoPost();
		videoInfoPost.setStatus(status);
		videoInfoPost.setVideoId(videoId);

		VideoInfoPostQuery videoInfoPostQuery = new VideoInfoPostQuery();
		// 必须保证视频状态为待审核才能进行审核，每一次进行审核前从数据库层面确认条件：where video_id = {video_id} and status = 2
		videoInfoPostQuery.setStatus(VideoStatusEnum.STATUS2.getStatus());
		videoInfoPostQuery.setVideoId(videoId);
		Integer auditCount = this.videoInfoPostMapper.updateByParam(videoInfoPost, videoInfoPostQuery);
		if (auditCount == 0) {
			throw new BusinessException("审核失败，请稍后重试！");
		}

		// 查询需要审核的视频文件
		VideoInfoFilePostQuery filePostQuery = new VideoInfoFilePostQuery();
		filePostQuery.setVideoId(videoId);
		List<VideoInfoFilePost> videoInfoFilePostList = this.videoInfoFilePostMapper.selectList(filePostQuery);

		// 检查视频文件信息是否完整，不完整则尝试从正式表补全
		for (VideoInfoFilePost filePost : videoInfoFilePostList) {
			if (filePost.getFilePath() == null || filePost.getFileSize() == null || filePost.getDuration() == null) {
				// 从正式表补全数据
				VideoInfoFile file = this.videoInfoFileMapper.selectByFileId(filePost.getFileId());
				if (file != null) {
					filePost.setFilePath(file.getFilePath());
					filePost.setFileSize(file.getFileSize());
					filePost.setDuration(file.getDuration());
					this.videoInfoFilePostMapper.update(filePost); // 更新审核表信息
				} else {
					log.error("无法从正式表补全文件信息，fileId: {}", filePost.getFileId());
					throw new BusinessException("文件信息不完整，审核失败");
				}
			}
		}

		// 如果审核不通过则直接返回
		if (VideoStatusEnum.STATUS4 == videoStatusEnum) {
			return;
		}

		// 查询视频信息
		VideoInfoPost infoPost = this.videoInfoPostMapper.selectByVideoId(videoId);
		VideoInfo dvVideoInfo = this.videoInfoMapper.selectByVideoId(videoId);

		// 如果是用户第一次投稿该视频，审核通过后给予奖励
		if (dvVideoInfo == null) {
			SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();
			//给用户加硬币奖励
			userInfoMapper.updateCoinCountInfo(infoPost.getUserId(),sysSettingDto.getPostVideoCoinCount());

		}

		VideoInfo videoInfo = CopyTools.copy(infoPost, VideoInfo.class);
		// 更新发布信息到正式表：已有视频就更新，没视频就插入
		this.videoInfoMapper.insertOrUpdate(videoInfo);

		/* 删除服务器中的原文件 */
		List<String> filePathList = redisComponent.getDelFileList(videoId);
		if (filePathList != null) {
			for (String path : filePathList) {
				File file = new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + path);
				if (file.exists()) {
					try {
						FileUtils.deleteDirectory(file);
					} catch (IOException e) {
						log.error("删除文件失败", e);
					}
				}
			}
		}

		// 删除正式表中的旧文件信息
		VideoInfoFileQuery videoInfoFileQuery = new VideoInfoFileQuery();
		videoInfoFileQuery.setVideoId(videoId);
		this.videoInfoFileMapper.deleteByParam(videoInfoFileQuery);

		// 将审核表中的文件信息同步到正式表
		List<VideoInfoFile> videoInfoFileList = CopyTools.copyList(videoInfoFilePostList, VideoInfoFile.class);
		this.videoInfoFileMapper.insertBatch(videoInfoFileList);



		// 清空与该视频有关的目录缓存
		redisComponent.cleanDelFileList(videoId);

		// 保存视频信息到 Elasticsearch
		esSearchComponent.saveDoc(videoInfo);
	}
}