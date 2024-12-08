package com.easylive.service.impl;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.po.VideoInfoPost;
import com.easylive.entity.query.*;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.*;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 视频信息 业务接口实现
 */
@Service("videoInfoService")
@Slf4j
public class VideoInfoServiceImpl implements VideoInfoService {
	@Resource
	private VideoInfoMapper<VideoInfo, VideoInfoQuery> videoInfoMapper;
	@Resource
	private VideoInfoPostMapper videoInfoPostMapper;
	@Resource
	private VideoInfoFilePostMapper videoInfoFilePostMapper;
	@Resource
	private RedisComponent redisComponent;
	@Resource
	private VideoInfoFileMapper videoInfoFileMapper;
	@Resource
	private VideoDanmuMapper videoDanmuMapper;
	@Resource
	private VideoCommentMapper videoCommentMapper;
	@Resource
	private AppConfig appConfig;
	@Resource
	private EsSearchComponent esSearchComponent;
	@Resource
	private UserInfoMapper<UserInfo,UserInfoQuery> userInfoMapper;

	private  static ExecutorService executorService = Executors.newFixedThreadPool(10);
	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<VideoInfo> findListByParam(VideoInfoQuery param) {
		return this.videoInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(VideoInfoQuery param) {
		return this.videoInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<VideoInfo> findListByPage(VideoInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<VideoInfo> list = this.findListByParam(param);
		PaginationResultVO<VideoInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(VideoInfo bean) {
		return this.videoInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<VideoInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<VideoInfo> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.videoInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(VideoInfo bean, VideoInfoQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(VideoInfoQuery param) {
		StringTools.checkParam(param);
		return this.videoInfoMapper.deleteByParam(param);
	}

	/**
	 * 根据VideoId获取对象
	 */
	@Override
	public VideoInfo getVideoInfoByVideoId(String videoId) {
		return this.videoInfoMapper.selectByVideoId(videoId);
	}

	/**
	 * 根据VideoId修改
	 */
	@Override
	public Integer updateVideoInfoByVideoId(VideoInfo bean, String videoId) {
		return this.videoInfoMapper.updateByVideoId(bean, videoId);
	}

	/**
	 * 根据VideoId删除
	 */
	@Override
	public Integer deleteVideoInfoByVideoId(String videoId) {
		return this.videoInfoMapper.deleteByVideoId(videoId);
	}

	//对比并修改互动状态
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeInteraction(String videoId, String userId, String interaction) {
		//关闭弹幕开启评论等互动设置无需重新审核，直接更改
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setInteraction(interaction);
		VideoInfoQuery videoInfoQuery = new VideoInfoQuery();
		videoInfoQuery.setVideoId(videoId);
		videoInfoQuery.setUserId(userId);
		videoInfoMapper.updateByParam(videoInfo,videoInfoQuery);

		VideoInfoPost videoInfoPost = new VideoInfoPost();
		videoInfoPost.setInteraction(interaction);
		VideoInfoPostQuery videoInfoPostQuery = new VideoInfoPostQuery();
		videoInfoPostQuery.setVideoId(videoId);
		videoInfoQuery.setUserId(userId);
		videoInfoPostMapper.updateByParam(videoInfoPost,videoInfoPostQuery);
	}

	//删除稿件
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteVideo(String videoId, String userId) {
		//只能删除自己的视频稿件 管理员删除不需要传入userId
		VideoInfo videoInfo = this.videoInfoMapper.selectByVideoId(videoId);
		if(videoInfo==null || userId!=null && !userId.equals(videoInfo.getUserId())){
			throw new BusinessException(ResponseCodeEnum.CODE_404);
		}

		SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();

		//减少用户因视频获得的奖励硬币
		userInfoMapper.updateCoinCountInfo(videoInfo.getUserId(),-sysSettingDto.getPostVideoCoinCount());

		//从es中删去视频信息
		esSearchComponent.delDoc(videoId);

		executorService.execute(()->{
			/*删除服务器内文件*/
			VideoInfoFileQuery videoInfoFileQuery = new VideoInfoFileQuery();
			videoInfoFileQuery.setVideoId(videoId);
			List<VideoInfoFile> videoInfoFileList = this.videoInfoFileMapper.selectList(videoInfoFileQuery);
			for (VideoInfoFile item : videoInfoFileList) {
				try {
					FileUtils.deleteDirectory(new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + item.getFilePath()));
				} catch (Exception e) {
					log.error("删除文件失败，文件路径：{}", item.getFilePath());
				}
			}

			/*删除数据库信息*/
			//删除视频信息
			VideoInfoQuery videoInfoQuery = new VideoInfoQuery();
			videoInfoQuery.setVideoId(videoId);
			videoInfoMapper.deleteByParam(videoInfoQuery);
			VideoInfoPostQuery videoInfoPostQuery = new VideoInfoPostQuery();
			videoInfoPostQuery.setVideoId(videoId);
			videoInfoPostMapper.deleteByParam(videoInfoPostQuery);
			//删除分p信息
			videoInfoFileMapper.deleteByParam(videoInfoFileQuery);
			VideoInfoFilePostQuery videoInfoFilePost = new VideoInfoFilePostQuery();
			videoInfoFilePost.setVideoId(videoId);
			videoInfoFilePostMapper.deleteByParam(videoInfoFilePost);
			//删除弹幕信息
			VideoDanmuQuery videoDanmuQuery = new VideoDanmuQuery();
			videoDanmuQuery.setVideoId(videoId);
			videoDanmuMapper.deleteByParam(videoDanmuQuery);
			//删除评论信息
			VideoCommentQuery videoCommentQuery = new VideoCommentQuery();
			videoCommentQuery.setVideoId(videoId);
			videoCommentMapper.deleteByParam(videoCommentQuery);

		});


	}

	//增加视频播放量
	@Override
	public void addReadCount(String videoId) {
		this.videoInfoMapper.updateCountInfo(videoId, UserActionTypeEnum.VIDEO_PLAY.getField(), 1);
	}

}