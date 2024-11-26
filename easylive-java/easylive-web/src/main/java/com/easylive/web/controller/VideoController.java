package com.easylive.web.controller;

import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.enums.VideoRecommendTypeEnum;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoInfoResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.VideoInfoFileService;
import com.easylive.service.VideoInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 主页视频 Controller
 */
@RestController
@RequestMapping("/video")
@Validated
public class VideoController extends ABaseController{

	@Resource
	private VideoInfoService videoInfoService;
	@Resource
	private VideoInfoFileService videoInfoFileService;
	/*首页推荐视频接口*/
	@RequestMapping("/loadRecommendVideo")
	public ResponseVO loadRecommendVideo(){
		VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
		videoInfoQuery.setQueryUserInfo(true);
		//根据创建时间降序排列推荐视频
		videoInfoQuery.setOrderBy("create_time desc");
		videoInfoQuery.setRecommendType(VideoRecommendTypeEnum.RECOMMEND.getType());
		List<VideoInfo> recommendVideoList = videoInfoService.findListByParam(videoInfoQuery);
		return getSuccessResponseVO(recommendVideoList);
	}

	/*首页非推荐视频加载接口*/
	@RequestMapping("/loadVideo")
	public ResponseVO loadVideo(Integer pCategoryId, Integer categoryId, Integer pageNo){
		VideoInfoQuery videoInfoQuery= new VideoInfoQuery();
		//根据视频分类过滤显示内容
		/*如果 pCategoryId 有值，则会查询某个父分类下的所有子分类视频。
		如果 categoryId 有值，则会查询属于指定分类的视频。*/
		videoInfoQuery.setCategoryId(categoryId);
		videoInfoQuery.setpCategoryId(pCategoryId);
		videoInfoQuery.setQueryUserInfo(true);
		//根据创建时间降序排列推荐视频
		videoInfoQuery.setOrderBy("create_time desc");
		videoInfoQuery.setRecommendType(VideoRecommendTypeEnum.NO_RECOMMEND.getType());
		PaginationResultVO resultVO  = videoInfoService.findListByPage(videoInfoQuery);
		return getSuccessResponseVO(resultVO);
	}

	@RequestMapping("/getVideoInfo")
	public ResponseVO getVideoInfo(@NotEmpty String videoId){
		VideoInfo videoInfo  = videoInfoService.getVideoInfoByVideoId(videoId);
		//用户的请求url地址中的videoId不正确则抛404
		if(videoInfo==null){
			throw new BusinessException(ResponseCodeEnum.CODE_404);
		}
		//TODO 获取用户行为：点赞投币收藏
		VideoInfoResultVO resultVO = new VideoInfoResultVO();
		resultVO.setVideoInfo(videoInfo);
		return getSuccessResponseVO(resultVO);
	}

	@RequestMapping("/loadVideoPList")
	public ResponseVO loadVideoPList(@NotEmpty String videoId){
		VideoInfoFileQuery videoInfoFileQuery = new VideoInfoFileQuery();
		videoInfoFileQuery.setVideoId(videoId);
		//根据文件index顺序排列分p信息
		videoInfoFileQuery.setOrderBy("file_index asc");
		List<VideoInfoFile> fileList = videoInfoFileService.findListByParam(videoInfoFileQuery);
		return getSuccessResponseVO(fileList);
	}
}