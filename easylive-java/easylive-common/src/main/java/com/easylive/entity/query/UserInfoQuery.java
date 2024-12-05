package com.easylive.entity.query;

import java.util.List;

/**
 * 参数
 */
public class UserInfoQuery extends BaseParam {


	/**
	 * 用户id
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 昵称
	 */
	private String nickName;

	private String nickNameFuzzy;

	/**
	 * 邮箱
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 密码
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 性别 0女 1男 2未知
	 */
	private Integer sex;

	/**
	 * 生日
	 */
	private String birthday;

	private String birthdayFuzzy;

	/**
	 * 学校
	 */
	private String school;

	private String schoolFuzzy;

	/**
	 * 个人简介
	 */
	private String personIntroduction;

	private String personIntroductionFuzzy;

	/**
	 * 加入时间
	 */
	private String joinTime;

	private String joinTimeStart;

	private String joinTimeEnd;

	/**
	 * 最后登录时间
	 */
	private String lastLoginTime;

	private String lastLoginTimeStart;

	private String lastLoginTimeEnd;

	/**
	 * 最后登录Ip
	 */
	private String lastLoginIp;

	private String lastLoginIpFuzzy;

	/**
	 * 0禁用 1正常
	 */
	private Integer status;

	/**
	 * 空间公告
	 */
	private String noticeInfo;

	private String noticeInfoFuzzy;

	/**
	 * 获得过的硬币总数
	 */
	private Integer totalCoinCount;

	/**
	 * 当前硬币数
	 */
	private Integer currentCoinCount;

	/**
	 * 主题
	 */
	private Integer theme;

	/**
	 * 头像
	 */
	private String avatar;

	private String avatarFuzzy;

	private List<String> userIdList;

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
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

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public String getNickName(){
		return this.nickName;
	}

	public void setNickNameFuzzy(String nickNameFuzzy){
		this.nickNameFuzzy = nickNameFuzzy;
	}

	public String getNickNameFuzzy(){
		return this.nickNameFuzzy;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmailFuzzy(String emailFuzzy){
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy(){
		return this.emailFuzzy;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}

	public void setSex(Integer sex){
		this.sex = sex;
	}

	public Integer getSex(){
		return this.sex;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String getBirthday(){
		return this.birthday;
	}

	public void setBirthdayFuzzy(String birthdayFuzzy){
		this.birthdayFuzzy = birthdayFuzzy;
	}

	public String getBirthdayFuzzy(){
		return this.birthdayFuzzy;
	}

	public void setSchool(String school){
		this.school = school;
	}

	public String getSchool(){
		return this.school;
	}

	public void setSchoolFuzzy(String schoolFuzzy){
		this.schoolFuzzy = schoolFuzzy;
	}

	public String getSchoolFuzzy(){
		return this.schoolFuzzy;
	}

	public void setPersonIntroduction(String personIntroduction){
		this.personIntroduction = personIntroduction;
	}

	public String getPersonIntroduction(){
		return this.personIntroduction;
	}

	public void setPersonIntroductionFuzzy(String personIntroductionFuzzy){
		this.personIntroductionFuzzy = personIntroductionFuzzy;
	}

	public String getPersonIntroductionFuzzy(){
		return this.personIntroductionFuzzy;
	}

	public void setJoinTime(String joinTime){
		this.joinTime = joinTime;
	}

	public String getJoinTime(){
		return this.joinTime;
	}

	public void setJoinTimeStart(String joinTimeStart){
		this.joinTimeStart = joinTimeStart;
	}

	public String getJoinTimeStart(){
		return this.joinTimeStart;
	}
	public void setJoinTimeEnd(String joinTimeEnd){
		this.joinTimeEnd = joinTimeEnd;
	}

	public String getJoinTimeEnd(){
		return this.joinTimeEnd;
	}

	public void setLastLoginTime(String lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTime(){
		return this.lastLoginTime;
	}

	public void setLastLoginTimeStart(String lastLoginTimeStart){
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public String getLastLoginTimeStart(){
		return this.lastLoginTimeStart;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd){
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public String getLastLoginTimeEnd(){
		return this.lastLoginTimeEnd;
	}

	public void setLastLoginIp(String lastLoginIp){
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginIp(){
		return this.lastLoginIp;
	}

	public void setLastLoginIpFuzzy(String lastLoginIpFuzzy){
		this.lastLoginIpFuzzy = lastLoginIpFuzzy;
	}

	public String getLastLoginIpFuzzy(){
		return this.lastLoginIpFuzzy;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setNoticeInfo(String noticeInfo){
		this.noticeInfo = noticeInfo;
	}

	public String getNoticeInfo(){
		return this.noticeInfo;
	}

	public void setNoticeInfoFuzzy(String noticeInfoFuzzy){
		this.noticeInfoFuzzy = noticeInfoFuzzy;
	}

	public String getNoticeInfoFuzzy(){
		return this.noticeInfoFuzzy;
	}

	public void setTotalCoinCount(Integer totalCoinCount){
		this.totalCoinCount = totalCoinCount;
	}

	public Integer getTotalCoinCount(){
		return this.totalCoinCount;
	}

	public void setCurrentCoinCount(Integer currentCoinCount){
		this.currentCoinCount = currentCoinCount;
	}

	public Integer getCurrentCoinCount(){
		return this.currentCoinCount;
	}

	public void setTheme(Integer theme){
		this.theme = theme;
	}

	public Integer getTheme(){
		return this.theme;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	public void setAvatarFuzzy(String avatarFuzzy){
		this.avatarFuzzy = avatarFuzzy;
	}

	public String getAvatarFuzzy(){
		return this.avatarFuzzy;
	}

}
