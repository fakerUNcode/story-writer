package com.easylive.web.controller;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.*;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.VideoInfoFileQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.VideoInfoResultVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserActionService;
import com.easylive.service.VideoInfoFileService;
import com.easylive.service.VideoInfoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	@Resource
	private UserActionService userActionService;
	@Resource
	private RedisComponent redisComponent;
	@Resource
	private EsSearchComponent esSearchComponent;


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
		TokenUserInfoDto  tokenUserInfoDto = getTokenUserInfoDto();
		List<UserAction> userActionList = new ArrayList<>();
		if(tokenUserInfoDto!=null){
			UserActionQuery actionQuery = new UserActionQuery();
			actionQuery.setVideoId(videoId);
			actionQuery.setUserId(tokenUserInfoDto.getUserId());
			actionQuery.setActionTypeArray(new Integer[] {UserActionTypeEnum.VIDEO_LIKE.getType(),UserActionTypeEnum.VIDEO_COLLECT.getType(),UserActionTypeEnum.VIDEO_COIN.getType()});
			userActionList = userActionService.findListByParam(actionQuery);
		}

		VideoInfoResultVO resultVO = new VideoInfoResultVO(videoInfo,userActionList);
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

	//视频播放
	@RequestMapping("/reportVideoPlayOnline")
	public ResponseVO reportVideoPlayOnline(@NotEmpty String fileId,@NotEmpty String deviceId){

		return getSuccessResponseVO(redisComponent.reportVideoPlayOnline(fileId,deviceId));
	}

	//搜索视频
	@RequestMapping("/search")
	public ResponseVO search(@NotEmpty String keyword,Integer orderType,Integer pageNo){
		//记录搜索热词
		redisComponent.addKeywordCount(keyword);
		PaginationResultVO resultVO = esSearchComponent.search(true,keyword,orderType,pageNo,PageSize.SIZE30.getSize());
		return getSuccessResponseVO(resultVO);
	}
	//获取最多的几个搜索热词
	@RequestMapping("/getSearchKeywordTop")
	public ResponseVO getSearchKeywordTop(){
		List<String> keywordList = redisComponent.getKeywordTop(Constants.LENGTH_10);
		return getSuccessResponseVO(keywordList);
	}

	//获取推荐视频
	@RequestMapping("/getVideoRecommend")
	public ResponseVO getVideoRecommend(@NotEmpty String keyword,@NotEmpty String videoId){
		List<VideoInfo> videoInfoList = esSearchComponent.search(false,keyword, SearchOrderTypeEnum.VIDEO_PLAY.getType(),1,PageSize.SIZE10.getSize()).getList();
		videoInfoList = videoInfoList.stream().filter(item->!item.getVideoId().equals(videoId)).collect(Collectors.toList());;
		return getSuccessResponseVO(videoInfoList);
	}

	//加载视频热榜
	@RequestMapping("/laodHotVideoList")
	public ResponseVO loadHotVideoList(Integer pageNo){
		VideoInfoQuery videoInfoQuery = new VideoInfoQuery();
		videoInfoQuery.setPageNo(pageNo);
		videoInfoQuery.setQueryUserInfo(true);
		videoInfoQuery.setOrderBy("play_count desc");
		PaginationResultVO resultVO = videoInfo
		return getSuccessResponseVO(videoInfoList);
	}

}