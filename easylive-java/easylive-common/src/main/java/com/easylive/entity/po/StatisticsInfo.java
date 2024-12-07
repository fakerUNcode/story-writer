package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 数据统计
 */
public class StatisticsInfo implements Serializable {


	/**
	 * 统计日期
	 */
	private String statisticsDate;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 数据统计类型
	 */
	private Integer dataType;

	/**
	 * 统计数值
	 */
	private Integer dataValue;


	public void setStatisticsDate(String statisticsDate){
		this.statisticsDate = statisticsDate;
	}

	public String getStatisticsDate(){
		return this.statisticsDate;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setDataType(Integer dataType){
		this.dataType = dataType;
	}

	public Integer getDataType(){
		return this.dataType;
	}

	public void setDataValue(Integer dataValue){
		this.dataValue = dataValue;
	}

	public Integer getDataValue(){
		return this.dataValue;
	}

	@Override
	public String toString (){
		return "统计日期:"+(statisticsDate == null ? "空" : statisticsDate)+"，用户ID:"+(userId == null ? "空" : userId)+"，数据统计类型:"+(dataType == null ? "空" : dataType)+"，统计数值:"+(dataValue == null ? "空" : dataValue);
	}
}
