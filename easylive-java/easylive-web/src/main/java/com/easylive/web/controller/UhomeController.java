package com.easylive.web.controller;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.UserInfo;
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

    @RequestMapping("saveTheme")
    public ResponseVO saveTheme(@Min(1) @Max(10) @NotNull Integer theme){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserInfo userInfo  = new UserInfo();
        userInfo.setTheme(theme);
        userInfoService.updateUserInfoByUserId(userInfo,tokenUserInfoDto.getUserId());
        return getSuccessResponseVO(null);
    }
}
