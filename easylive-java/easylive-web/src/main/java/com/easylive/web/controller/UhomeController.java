package com.easylive.web.controller;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.PageSize;
import com.easylive.entity.enums.UserActionTypeEnum;
import com.easylive.entity.enums.VideoOrderTypeEnum;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.query.UserActionQuery;
import com.easylive.entity.query.UserFocusQuery;
import com.easylive.entity.query.VideoInfoQuery;
import com.easylive.entity.vo.PaginationResultVO;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.entity.vo.UserInfoVO;
import com.easylive.service.UserActionService;
import com.easylive.service.UserFocusService;
import com.easylive.service.UserInfoService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.CopyTools;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.*;

@RestController
@RequestMapping("/uhome")
@Validated
public class UhomeController extends ABaseController{
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private VideoInfoService videoInfoService;
    @Resource
    private UserFocusService userFocusService;
    @Resource
    private UserActionService userActionService;

    //用户主页信息获取
    @RequestMapping("/getUserInfo")
    public ResponseVO getUserInfo(@NotEmpty String userId){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserInfo userInfo = userInfoService.getUserDetailInfo(tokenUserInfoDto==null?null:tokenUserInfoDto.getUserId(),userId);

        UserInfoVO userInfoVO = CopyTools.copy(userInfo, UserInfoVO.class);

        return getSuccessResponseVO(userInfoVO);
    }

    //用户主页信息修改
    @RequestMapping("updateUserInfo")
    public ResponseVO updateUserInfo(@NotEmpty @Size(max=20)String nickName,
                                     @NotEmpty @Size(max=100)String avatar,
                                     @NotNull Integer sex,
                                     @Size(max=100)String birthday,
                                     @Size(max=150)String school,
                                     @Size(max=80) String personIntroduction,
                                     @Size(max=300) String noticeInfo){

        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(tokenUserInfoDto.getUserId());
        userInfo.setNickName(nickName);
        userInfo.setAvatar(avatar);
        userInfo.setSex(sex);
        userInfo.setBirthday(birthday);
        userInfo.setSchool(school);
        userInfo.setPersonIntroduction(personIntroduction);
        userInfo.setNoticeInfo(noticeInfo);

        userInfoService.updateUserInfo(userInfo,tokenUserInfoDto);
        return getSuccessResponseVO(null);
    }

    //保存主题
    @RequestMapping("saveTheme")
    public ResponseVO saveTheme(@Min(1) @Max(10) @NotNull Integer theme){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserInfo userInfo  = new UserInfo();
        userInfo.setTheme(theme);
        userInfoService.updateUserInfoByUserId(userInfo,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }

    //关注用户
    @RequestMapping("/focus")
    public ResponseVO focus(@NotEmpty String focusUserId){

        userFocusService.focusUser(getTokenUserInfoDto().getUserId(),focusUserId);
        return getSuccessResponseVO(null);
    }
    //取消关注用户
    @RequestMapping("/cancelFocus")
    public ResponseVO cancelFocus(@NotEmpty String focusUserId){

        userFocusService.cancelFocus(getTokenUserInfoDto().getUserId(),focusUserId);
        return getSuccessResponseVO(null);
    }

    //加载关注列表
    @RequestMapping("/loadFocusList")
    public ResponseVO loadFocusList(Integer pageNo){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserFocusQuery focusQuery = new UserFocusQuery();
        focusQuery.setUserId(tokenUserInfoDto.getUserId());
        focusQuery.setPageNo(pageNo);
        focusQuery.setOrderBy("focus_time desc");
        focusQuery.setQueryType(Constants.ZERO);
        PaginationResultVO resultVO = userFocusService.findListByPage(focusQuery);
        return getSuccessResponseVO(resultVO);
    }

    //加载粉丝列表
    @RequestMapping("/loadFansList")
    public ResponseVO loadFansCount(Integer pageNo){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserFocusQuery focusQuery = new UserFocusQuery();
        focusQuery.setFocusUserId(tokenUserInfoDto.getUserId());
        focusQuery.setPageNo(pageNo);
        focusQuery.setOrderBy("focus_time desc");
        focusQuery.setQueryType(Constants.ONE);
        PaginationResultVO resultVO = userFocusService.findListByPage(focusQuery);
        return getSuccessResponseVO(resultVO);
    }

    //加载视频列表
    @RequestMapping("/loadVideoList")
    public ResponseVO loadVideoList(@NotEmpty String userId,Integer type, Integer pageNo,String videoName, Integer orderType){

        VideoInfoQuery infoQuery = new VideoInfoQuery();
        if(type!=null){
            infoQuery.setPageSize(PageSize.SIZE10.getSize());
        }
        //获取视频排序类型：最新发布、最多点赞、最多收藏
        VideoOrderTypeEnum videoOrderTypeEnum = VideoOrderTypeEnum.getByType(orderType);
        //没传入则默认按最新发布排序
        if(videoOrderTypeEnum==null){
            videoOrderTypeEnum = VideoOrderTypeEnum.CREATE_TIME;
        }
        infoQuery.setOrderBy(videoOrderTypeEnum.getField() + " desc");
        infoQuery.setVideoNameFuzzy(videoName);
        infoQuery.setPageNo(pageNo);
        infoQuery.setUserId(userId);
        PaginationResultVO resultVO = videoInfoService.findListByPage(infoQuery);
        return getSuccessResponseVO(resultVO);
    }

    //加载用户收藏视频
    @RequestMapping("/loadUserCollection")
    public ResponseVO loadUserCollection(@NotEmpty String userId, Integer pageNo){

        UserActionQuery actionQuery = new UserActionQuery();
        actionQuery.setActionType(UserActionTypeEnum.VIDEO_COLLECT.getType());
        actionQuery.setUserId(userId);
        actionQuery.setPageNo(pageNo);
        //按收藏时间排序
        actionQuery.setOrderBy("action_time desc");
        actionQuery.setQueryVideoInfo(true);
        PaginationResultVO resultVO = userActionService.findListByPage(actionQuery);
        return getSuccessResponseVO(resultVO);
    }



}
