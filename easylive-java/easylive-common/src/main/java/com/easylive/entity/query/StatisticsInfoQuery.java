package com.easylive.entity.query;



/**
 * 数据统计参数
 */
public class StatisticsInfoQuery extends BaseParam {


	/**
	 * 统计日期
	 */
	private String statisticsDate;

	private String statisticsDateFuzzy;

	/**
	 * 用户ID
	 */
	private String userId;

	private String userIdFuzzy;

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

	public void setStatisticsDateFuzzy(String statisticsDateFuzzy){
		this.statisticsDateFuzzy = statisticsDateFuzzy;
	}

	public String getStatisticsDateFuzzy(){
		return this.statisticsDateFuzzy;
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

}