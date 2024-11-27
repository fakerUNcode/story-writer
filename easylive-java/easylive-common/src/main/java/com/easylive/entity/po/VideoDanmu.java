package com.easylive.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 视频弹幕
 */
public class VideoDanmu implements Serializable {


	/**
	 * 自增ID
	 */
	private Integer danmuId;

	/**
	 * 视频ID
	 */
	private String videoId;

	/**
	 * 唯一ID
	 */
	private String fileId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postTime;

	/**
	 * 内容
	 */
	private String text;

	/**
	 * 展示位置
	 */
	private Integer mode;

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 展示时间
	 */
	private Integer time;


	public void setDanmuId(Integer danmuId){
		this.danmuId = danmuId;
	}

	public Integer getDanmuId(){
		return this.danmuId;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return this.videoId;
	}

	public void setFileId(String fileId){
		this.fileId = fileId;
	}

	public String getFileId(){
		return this.fileId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setPostTime(Date postTime){
		this.postTime = postTime;
	}

	public Date getPostTime(){
		return this.postTime;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return this.text;
	}

	public void setMode(Integer mode){
		this.mode = mode;
	}

	public Integer getMode(){
		return this.mode;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return this.color;
	}

	public void setTime(Integer time){
		this.time = time;
	}

	public Integer getTime(){
		return this.time;
	}

	@Override
	public String toString (){
		return "自增ID:"+(danmuId == null ? "空" : danmuId)+"，视频ID:"+(videoId == null ? "空" : videoId)+"，唯一ID:"+(fileId == null ? "空" : fileId)+"，用户ID:"+(userId == null ? "空" : userId)+"，发布时间:"+(postTime == null ? "空" : DateUtil.format(postTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，内容:"+(text == null ? "空" : text)+"，展示位置:"+(mode == null ? "空" : mode)+"，颜色:"+(color == null ? "空" : color)+"，展示时间:"+(time == null ? "空" : time);
	}
}
