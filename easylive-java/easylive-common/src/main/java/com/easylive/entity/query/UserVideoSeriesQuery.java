package com.easylive.entity.query;

import java.util.Date;


/**
 * 用户视频序列归档参数
 */
public class UserVideoSeriesQuery extends BaseParam {


	/**
	 * 列表ID
	 */
	private Integer seriesId;

	/**
	 * 列表名称
	 */
	private String seriesName;

	private String seriesNameFuzzy;

	/**
	 * 描述
	 */
	private String seriesDescription;

	private String seriesDescriptionFuzzy;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 更新时间
	 */
	private String updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;


	public void setSeriesId(Integer seriesId){
		this.seriesId = seriesId;
	}

	public Integer getSeriesId(){
		return this.seriesId;
	}

	public void setSeriesName(String seriesName){
		this.seriesName = seriesName;
	}

	public String getSeriesName(){
		return this.seriesName;
	}

	public void setSeriesNameFuzzy(String seriesNameFuzzy){
		this.seriesNameFuzzy = seriesNameFuzzy;
	}

	public String getSeriesNameFuzzy(){
		return this.seriesNameFuzzy;
	}

	public void setSeriesDescription(String seriesDescription){
		this.seriesDescription = seriesDescription;
	}

	public String getSeriesDescription(){
		return this.seriesDescription;
	}

	public void setSeriesDescriptionFuzzy(String seriesDescriptionFuzzy){
		this.seriesDescriptionFuzzy = seriesDescriptionFuzzy;
	}

	public String getSeriesDescriptionFuzzy(){
		return this.seriesDescriptionFuzzy;
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

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public Integer getSort(){
		return this.sort;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTimeStart(String updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart(){
		return this.updateTimeStart;
	}
	public void setUpdateTimeEnd(String updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd(){
		return this.updateTimeEnd;
	}

}
