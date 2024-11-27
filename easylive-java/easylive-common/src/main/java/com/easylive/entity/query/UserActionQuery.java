package com.easylive.entity.query;

import java.util.Date;


/**
 * 用户行为 点赞、评论参数
 */
public class UserActionQuery extends BaseParam {


	/**
	 * 自增ID
	 */
	private Integer actionId;

	/**
	 * 视频ID
	 */
	private String videoId;

	private String videoIdFuzzy;

	/**
	 * 视频用户ID
	 */
	private String videoUserId;

	private String videoUserIdFuzzy;

	/**
	 * 评论ID
	 */
	private Integer commentId;

	/**
	 * 0:评论点赞 1:讨厌评论 2:视频点赞 3:视频收藏 4:视频投币
	 */
	private Integer actionType;

	/**
	 * 数量
	 */
	private Integer actionCount;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 操作时间
	 */
	private String actionTime;

	private String actionTimeStart;

	private String actionTimeEnd;


	public void setActionId(Integer actionId){
		this.actionId = actionId;
	}

	public Integer getActionId(){
		return this.actionId;
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

	public void setVideoUserId(String videoUserId){
		this.videoUserId = videoUserId;
	}

	public String getVideoUserId(){
		return this.videoUserId;
	}

	public void setVideoUserIdFuzzy(String videoUserIdFuzzy){
		this.videoUserIdFuzzy = videoUserIdFuzzy;
	}

	public String getVideoUserIdFuzzy(){
		return this.videoUserIdFuzzy;
	}

	public void setCommentId(Integer commentId){
		this.commentId = commentId;
	}

	public Integer getCommentId(){
		return this.commentId;
	}

	public void setActionType(Integer actionType){
		this.actionType = actionType;
	}

	public Integer getActionType(){
		return this.actionType;
	}

	public void setActionCount(Integer actionCount){
		this.actionCount = actionCount;
	}

	public Integer getActionCount(){
		return this.actionCount;
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

	public void setActionTime(String actionTime){
		this.actionTime = actionTime;
	}

	public String getActionTime(){
		return this.actionTime;
	}

	public void setActionTimeStart(String actionTimeStart){
		this.actionTimeStart = actionTimeStart;
	}

	public String getActionTimeStart(){
		return this.actionTimeStart;
	}
	public void setActionTimeEnd(String actionTimeEnd){
		this.actionTimeEnd = actionTimeEnd;
	}

	public String getActionTimeEnd(){
		return this.actionTimeEnd;
	}

}
