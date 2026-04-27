package com.easylive.service.impl;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.enums.VideoRecommendTypeEnum;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFilePost;
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

	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

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
		// 1. 基准查询：统一从源头稿件表 (video_info_post) 查询视频记录，确保各种状态的视频都能查到
		// 【修复点】：增加 (VideoInfoPost) 强制类型转换解决 Object 报错
		VideoInfoPost videoInfoPost = (VideoInfoPost) this.videoInfoPostMapper.selectByVideoId(videoId);

		// 判断权限：如果是用户端调用（userId不为空），必须保证是本人；如果是管理端调用（userId为空），则直接放行
		if (videoInfoPost == null || (userId != null && !userId.equals(videoInfoPost.getUserId()))) {
			throw new BusinessException(ResponseCodeEnum.CODE_404);
		}

		// 2. 查询正式表，判断该视频是否曾经正式发布过（审核通过进入主表）
		VideoInfo videoInfo = this.videoInfoMapper.selectByVideoId(videoId);

		// 3. 只有当视频存在于正式主表时，才进行扣除硬币和清理 ES 索引的操作
		if (videoInfo != null) {
			SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();
			// 减少用户因视频获得的奖励硬币 (防止转码失败的视频扣除用户原始硬币)
			userInfoMapper.updateCoinCountInfo(videoInfoPost.getUserId(), -sysSettingDto.getPostVideoCoinCount());
			// 从es中删去视频信息
			esSearchComponent.delDoc(videoId);
		}

		// 4. 异步清理底层文件与所有相关的数据库记录
		executorService.execute(() -> {
			try {
				// 【修复点】：文件清理必须基于 post 稿件表查询，因为未过审/转码失败的视频，在正式文件表中没有记录
				VideoInfoFilePostQuery filePostQuery = new VideoInfoFilePostQuery();
				filePostQuery.setVideoId(videoId);
				// 【修复点】：增加 (List<VideoInfoFilePost>) 强制类型转换
				@SuppressWarnings("unchecked")
				List<VideoInfoFilePost> videoInfoFilePostList = (List<VideoInfoFilePost>) this.videoInfoFilePostMapper.selectList(filePostQuery);

				for (VideoInfoFilePost item : videoInfoFilePostList) {
					try {
						// 【修复点】：使用原生 Java 判空替换 StringTools.isNotEmpty
						if (item.getFilePath() != null && !item.getFilePath().trim().isEmpty()) {
							FileUtils.deleteDirectory(new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + item.getFilePath()));
						}
					} catch (Exception e) {
						log.error("删除本地物理文件失败，文件路径：{}", item.getFilePath());
					}
				}

				/* 删除数据库信息：无论正式表里有没有，执行 delete 操作都是安全的（没数据只会返回影响行数0） */
				// 删除稿件表（源头表）
				VideoInfoPostQuery postQuery = new VideoInfoPostQuery();
				postQuery.setVideoId(videoId);
				videoInfoPostMapper.deleteByParam(postQuery);

				videoInfoFilePostMapper.deleteByParam(filePostQuery);

				// 删除主业务表
				VideoInfoQuery infoQuery = new VideoInfoQuery();
				infoQuery.setVideoId(videoId);
				videoInfoMapper.deleteByParam(infoQuery);

				VideoInfoFileQuery fileQuery = new VideoInfoFileQuery();
				fileQuery.setVideoId(videoId);
				videoInfoFileMapper.deleteByParam(fileQuery);

				// 删除互动信息（弹幕、评论）
				VideoDanmuQuery videoDanmuQuery = new VideoDanmuQuery();
				videoDanmuQuery.setVideoId(videoId);
				videoDanmuMapper.deleteByParam(videoDanmuQuery);

				VideoCommentQuery videoCommentQuery = new VideoCommentQuery();
				videoCommentQuery.setVideoId(videoId);
				videoCommentMapper.deleteByParam(videoCommentQuery);

			} catch (Exception e) {
				log.error("异步彻底删除视频关联数据异常，videoId: {}", videoId, e);
			}
		});
	}

	//增加视频播放量
	@Override
	public void addReadCount(String videoId) {
		this.videoInfoMapper.updateCountInfo(videoId, UserActionTypeEnum.VIDEO_PLAY.getField(), 1);
	}

	@Override
	public void recommendVideo(String videoId) {
		VideoInfo videoInfo = videoInfoMapper.selectByVideoId(videoId);
		//未审核的不允许绕过前端修改数据来推荐视频
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_600);
		}
		Integer recommendType = null;

		//已经推荐过的再次点击则为取消推荐
		if (VideoRecommendTypeEnum.RECOMMEND.getType().equals(videoInfo.getRecommendType())){
			recommendType = VideoRecommendTypeEnum.NO_RECOMMEND.getType();
		}else {
			recommendType = VideoRecommendTypeEnum.RECOMMEND.getType();
		}
		VideoInfo updateInfo = new VideoInfo();
		updateInfo.setRecommendType(recommendType);
		videoInfoMapper.updateByVideoId(updateInfo,videoId);

	}
}