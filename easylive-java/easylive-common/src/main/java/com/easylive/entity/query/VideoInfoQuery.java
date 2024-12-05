package com.easylive.entity.query;

/**
 * 视频信息参数
 */
public class VideoInfoQuery extends BaseParam {


	/**
	 * 视频ID
	 */
	private String videoId;

	private String videoIdFuzzy;

	/**
	 * 视频封面
	 */
	private String videoCover;

	private String videoCoverFuzzy;

	/**
	 * 视频名称
	 */
	private String videoName;

	private String videoNameFuzzy;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 最后更新时间
	 */
	private String lastUpdateTime;

	private String lastUpdateTimeStart;

	private String lastUpdateTimeEnd;

	/**
	 * 父级分类ID
	 */
	private Integer pCategoryId;

	/**
	 * 分类ID
	 */
	private Integer categoryId;

	/**
	 * 0:自制作 1:转载
	 */
	private Integer postType;

	/**
	 * 原资源说明
	 */
	private String originInfo;

	private String originInfoFuzzy;

	/**
	 * 标签
	 */
	private String tags;

	private String tagsFuzzy;

	/**
	 * 简介
	 */
	private String introduction;

	private String introductionFuzzy;

	/**
	 * 互动设置
	 */
	private String interaction;

	private String interactionFuzzy;

	/**
	 * 持续时间（秒）
	 */
	private Integer duration;

	/**
	 * 播放数量
	 */
	private Integer playCount;

	/**
	 * 点赞数量
	 */
	private Integer likeCount;

	/**
	 * 弹幕数量
	 */
	private Integer danmuCount;

	/**
	 * 评论数量
	 */
	private Integer commentCount;

	/**
	 * 投币数量
	 */
	private Integer coinCount;

	/**
	 * 收藏数量
	 */
	private Integer collectCount;

	/**
	 * 是否推荐 0:未推荐 1:已推荐
	 */
	private Integer recommendType;

	/**
	 * 最后播放时间
	 */
	private String lastPlayTime;

	/*播放的最近24个小时*/
	private Integer lastPlayHour;

	public Integer getLastPlayHour() {
		return lastPlayHour;
	}

	public void setLastPlayHour(Integer lastPlayHour) {
		this.lastPlayHour = lastPlayHour;
	}

	private String lastPlayTimeStart;

	private String lastPlayTimeEnd;
	private Boolean queryUserInfo;

	private String[] videoIdArray;

	private String[] excludeVideoIdArray;

	public String[] getExcludeVideoIdArray() {
		return excludeVideoIdArray;
	}

	public void setExcludeVideoIdArray(String[] excludeVideoIdArray) {
		this.excludeVideoIdArray = excludeVideoIdArray;
	}

	public String[] getVideoIdArray() {
		return videoIdArray;
	}

	public void setVideoIdArray(String[] videoIdArray) {
		this.videoIdArray = videoIdArray;
	}

	public Boolean getQueryUserInfo() {
		return queryUserInfo;
	}

	public void setQueryUserInfo(Boolean queryUserInfo) {
		this.queryUserInfo = queryUserInfo;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setVideoIdFuzzy(String videoIdFuzzy){
		this.videoIdFuzzy = videoIdFuzzy;
	}

	public String getVideoIdFuzzy(){
		return this.videoIdFuzzy;
	}

	public void setVideoCover(String videoCover){
		this.videoCover = videoCover;
	}

	public String getVideoCover(){
		return this.videoCover;
	}

	public void setVideoCoverFuzzy(String videoCoverFuzzy){
		this.videoCoverFuzzy = videoCoverFuzzy;
	}

	public String getVideoCoverFuzzy(){
		return this.videoCoverFuzzy;
	}

	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getVideoName(){
		return this.videoName;
	}

	public void setVideoNameFuzzy(String videoNameFuzzy){
		this.videoNameFuzzy = videoNameFuzzy;
	}

	public String getVideoNameFuzzy(){
		return this.videoNameFuzzy;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserIdFuzzy(String userIdFuzzy){
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy(){
		return this.userIdFuzzy;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeStart(String createTimeStart){
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart(){
		return this.createTimeStart;
	}
	public void setCreateTimeEnd(String createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd(){
		return this.createTimeEnd;
	}

	public void setLastUpdateTime(String lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	public void setLastUpdateTimeStart(String lastUpdateTimeStart){
		this.lastUpdateTimeStart = lastUpdateTimeStart;
	}

	public String getLastUpdateTimeStart(){
		return this.lastUpdateTimeStart;
	}
	public void setLastUpdateTimeEnd(String lastUpdateTimeEnd){
		this.lastUpdateTimeEnd = lastUpdateTimeEnd;
	}

	public String getLastUpdateTimeEnd(){
		return this.lastUpdateTimeEnd;
	}

	public void setpCategoryId(Integer pCategoryId){
		this.pCategoryId = pCategoryId;
	}

	public Integer getpCategoryId(){
		return this.pCategoryId;
	}

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Integer getCategoryId(){
		return this.categoryId;
	}

	public void setPostType(Integer postType){
		this.postType = postType;
	}

	public Integer getPostType(){
		return this.postType;
	}

	public void setOriginInfo(String originInfo){
		this.originInfo = originInfo;
	}

	public String getOriginInfo(){
		return this.originInfo;
	}

	public void setOriginInfoFuzzy(String originInfoFuzzy){
		this.originInfoFuzzy = originInfoFuzzy;
	}

	public String getOriginInfoFuzzy(){
		return this.originInfoFuzzy;
	}

	public void setTags(String tags){
		this.tags = tags;
	}

	public String getTags(){
		return this.tags;
	}

	public void setTagsFuzzy(String tagsFuzzy){
		this.tagsFuzzy = tagsFuzzy;
	}

	public String getTagsFuzzy(){
		return this.tagsFuzzy;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
	}

	public void setIntroductionFuzzy(String introductionFuzzy){
		this.introductionFuzzy = introductionFuzzy;
	}

	public String getIntroductionFuzzy(){
		return this.introductionFuzzy;
	}

	public void setInteraction(String interaction){
		this.interaction = interaction;
	}

	public String getInteraction(){
		return this.interaction;
	}

	public void setInteractionFuzzy(String interactionFuzzy){
		this.interactionFuzzy = interactionFuzzy;
	}

	public String getInteractionFuzzy(){
		return this.interactionFuzzy;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration(){
		return this.duration;
	}

	public void setPlayCount(Integer playCount){
		this.playCount = playCount;
	}

	public Integer getPlayCount(){
		return this.playCount;
	}

	public void setLikeCount(Integer likeCount){
		this.likeCount = likeCount;
	}

	public Integer getLikeCount(){
		return this.likeCount;
	}

	public void setDanmuCount(Integer danmuCount){
		this.danmuCount = danmuCount;
	}

	public Integer getDanmuCount(){
		return this.danmuCount;
	}

	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}

	public Integer getCommentCount(){
		return this.commentCount;
	}

	public void setCoinCount(Integer coinCount){
		this.coinCount = coinCount;
	}

	public Integer getCoinCount(){
		return this.coinCount;
	}

	public void setCollectCount(Integer collectCount){
		this.collectCount = collectCount;
	}

	public Integer getCollectCount(){
		return this.collectCount;
	}

	public void setRecommendType(Integer recommendType){
		this.recommendType = recommendType;
	}

	public Integer getRecommendType(){
		return this.recommendType;
	}

	public void setLastPlayTime(String lastPlayTime){
		this.lastPlayTime = lastPlayTime;
	}

	public String getLastPlayTime(){
		return this.lastPlayTime;
	}

	public void setLastPlayTimeStart(String lastPlayTimeStart){
		this.lastPlayTimeStart = lastPlayTimeStart;
	}

	public String getLastPlayTimeStart(){
		return this.lastPlayTimeStart;
	}
	public void setLastPlayTimeEnd(String lastPlayTimeEnd){
		this.lastPlayTimeEnd = lastPlayTimeEnd;
	}

	public String getLastPlayTimeEnd(){
		return this.lastPlayTimeEnd;
	}

}
