package com.easylive.web.controller;

import com.easylive.web.annotation.GlobalInterceptor;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.po.UserAction;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserActionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/userAction")
public class UserActionController extends ABaseController{
    @Resource
    private UserActionService userActionService;

    @RequestMapping("/doAction")
    @GlobalInterceptor(checkLogin = true)
    public ResponseVO doAction(@NotEmpty String videoId,
                               @NotNull Integer actionType,
                               @Max(2) @Min(1) Integer actionCount,
                               Integer commentId){
        UserAction userAction = new UserAction();
        userAction.setVideoId(videoId);
        userAction.setUserId(getTokenUserInfoDto().getUserId());
        userAction.setActionType(actionType);
        //未传入actionCount则设为1
        actionCount = actionCount==null? Constants.ONE:actionCount;
        userAction.setActionCount(actionCount);
        //用户未评论则默认置commentId为1
        commentId = commentId == null ? Constants.ZERO : commentId;
        userAction.setCommentId(commentId);
        userActionService.saveAction(userAction);
        return getSuccessResponseVO(null);

    }
}
