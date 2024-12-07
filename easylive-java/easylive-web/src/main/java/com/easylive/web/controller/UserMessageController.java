package com.easylive.web.controller;

import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.MessageReadTypeEnum;
import com.easylive.entity.query.UserMessageQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserMessageService;
import com.easylive.web.annotation.GlobalInterceptor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Validated
@RequestMapping("/message")
public class UserMessageController extends ABaseController{

    @Resource
    private UserMessageService userMessageService;

    @RequestMapping("/getNoReadCount")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO getNoReadCount(){
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UserMessageQuery messageQuery = new UserMessageQuery();
        messageQuery.setUserId(tokenUserInfoDto.getUserId());
        messageQuery.setReadType(MessageReadTypeEnum.NO_READ.getType());
        Integer count = userMessageService.findCountByParam(messageQuery);
        return getSuccessResponseVO(count);
    }

}
