package com.easylive.entity.query;

import java.util.Date;


/**
 * 参数
 */
public class UserFocusQuery extends BaseParam {


	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 用户ID
	 */
	private String focusUserId;

	private String focusUserIdFuzzy;

	/**
	 * 
	 */
	private String focusTime;

	private String focusTimeStart;

	private String focusTimeEnd;


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

	public void setFocusUserId(String focusUserId){
		this.focusUserId = focusUserId;
	}

	public String getFocusUserId(){
		return this.focusUserId;
	}

	public void setFocusUserIdFuzzy(String focusUserIdFuzzy){
		this.focusUserIdFuzzy = focusUserIdFuzzy;
	}

	public String getFocusUserIdFuzzy(){
		return this.focusUserIdFuzzy;
	}

	public void setFocusTime(String focusTime){
		this.focusTime = focusTime;
	}

	public String getFocusTime(){
		return this.focusTime;
	}

	public void setFocusTimeStart(String focusTimeStart){
		this.focusTimeStart = focusTimeStart;
	}

	public String getFocusTimeStart(){
		return this.focusTimeStart;
	}
	public void setFocusTimeEnd(String focusTimeEnd){
		this.focusTimeEnd = focusTimeEnd;
	}

	public String getFocusTimeEnd(){
		return this.focusTimeEnd;
	}

}
