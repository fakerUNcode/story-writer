package com.easylive.admin.controller;

import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController extends ABaseController{
    @Resource
    private UserInfoService userInfoService;

    //加载用户稿件
    @RequestMapping("/loadUser")
    public ResponseVO loadUser(UserInfoQuery userInfoQuery){
        userInfoQuery.setOrderBy("join_time desc");
        return getSuccessResponseVO(userInfoService.findListByPage(userInfoQuery));
    }

    @RequestMapping("/changeStatus")
    public ResponseVO changeStatus(String userId,Integer status){
        userInfoService.changeUserStatus(userId,status);
        return getSuccessResponseVO(null);
    }

}

