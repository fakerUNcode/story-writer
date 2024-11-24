package com.easylive.service.impl;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoStatusEnum;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.query.VideoInfoFilePostQuery;
import com.easylive.entity.query.VideoInfoPostQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.VideoInfoFilePostMapper;
import com.easylive.mappers.VideoInfoPostMapper;
import com.easylive.service.VideoInfoPostService;
import com.easylive.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 视频信息 业务接口实现
 */
@Service("videoInfoPostService")
public class VideoInfoPostServiceImpl implements VideoInfoPostService {
	@Resource
	private RedisComponent redisComponent;

	@Resource
	private VideoInfoPostMapper<VideoInfoPost, VideoInfoPostQuery> videoInfoPostMapper;
	@Resource
	private VideoInfoFilePostMapper <VideoInfoFilePost,VideoInfoFilePostQuery> videoInfoFilePostMapper;

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
		List<VideoInfoFilePost> addFileList;
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
	}

	private Boolean changeVideoInfo(VideoInfoPost videoInfoPost){
		VideoInfoPost dbInfo = this.videoInfoPostMapper.selectByVideoId((videoInfoPost.getVideoId()));
		//检测标题、封面、标签、简介等信息是否更改
		if(!videoInfoPost.getVideoCover().equals(dbInfo.getVideoCover()) ||
				!videoInfoPost.getVideoName().equals(dbInfo.getVideoName()) ||
				!videoInfoPost.getTags().equals(dbInfo.getTags()) ||
				!videoInfoPost.getIntroduction().equals(dbInfo.getIntroduction())){
			return true;
		}
		return false;
	}
}