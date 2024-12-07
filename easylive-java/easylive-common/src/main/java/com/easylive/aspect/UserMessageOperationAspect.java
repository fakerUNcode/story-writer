package com.easylive.aspect;

import com.easylive.annotation.RecordUserMessage;
import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.enums.MessageTypeEnum;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.UserMessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
@Slf4j
public class UserMessageOperationAspect {
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private UserMessageService userMessageService;
    private static final String PARAMETERS_VIDEO_ID = "videoId";
    private static final String PARAMETERS_ACTION_TYPE = "actionType";
    private static final String PARAMETERS_REPLY_COMMENTID = "replyCommentId";
    private static final String PARAMETERS_CONTENT = "content";
    private static final String PARAMETERS_AUDIT_REJECT_REASON = "reason";

    @Around("@annotation(com.easylive.annotation.RecordUserMessage)")
    public ResponseVO interceptorDo(ProceedingJoinPoint point) throws Throwable {
        // 执行目标方法
        ResponseVO responseVO = (ResponseVO) point.proceed();

        // 获取方法信息
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        RecordUserMessage recordUserMessage = method.getAnnotation(RecordUserMessage.class);

        if (recordUserMessage != null) {
            // 默认从注解中获取 messageType
            MessageTypeEnum messageTypeEnum = recordUserMessage.messageType();

            // 动态解析参数，调整 messageTypeEnum
            Object[] args = point.getArgs();
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                if ("actionType".equals(parameters[i].getName()) && args[i] instanceof Integer) {
                    Integer actionType = (Integer) args[i];
                    // 直接使用 MessageTypeEnum 的静态方法解析类型
                    MessageTypeEnum dynamicMessageType = MessageTypeEnum.getByType(actionType);
                    if (dynamicMessageType != null) {
                        messageTypeEnum = dynamicMessageType;
                    }
                }
            }

            // 调用保存消息方法，并传入动态调整的 messageTypeEnum
            saveMessage(recordUserMessage, point.getArgs(), method.getParameters(), messageTypeEnum);
        }
        return responseVO;
    }


    private void saveMessage(RecordUserMessage recordUserMessage, Object[] arguments, Parameter[] parameters, MessageTypeEnum messageTypeEnum) {
        String videoId = null;
        Integer replyCommentId = null;
        String content = null;
        String reason = null;

        // 解析参数
        for (int i = 0; i < parameters.length; i++) {
            if ("videoId".equals(parameters[i].getName())) {
                videoId = (String) arguments[i];
            } else if ("replyCommentId".equals(parameters[i].getName())) {
                replyCommentId = (Integer) arguments[i];
            } else if ("content".equals(parameters[i].getName())) {
                content = (String) arguments[i];
            } else if ("reason".equals(parameters[i].getName())) {
                reason = (String) arguments[i];
            }
        }

        // 获取用户信息
        TokenUserInfoDto tokenUserInfoDto = this.getTokenUserInfoDto();

        // 保存用户消息
        userMessageService.saveUserMessage(
                videoId,
                tokenUserInfoDto == null ? null : tokenUserInfoDto.getUserId(),
                messageTypeEnum, // 使用动态调整的 messageTypeEnum
                content,
                replyCommentId
        );
    }


    private TokenUserInfoDto getTokenUserInfoDto() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Constants.TOKEN_WEB);
        return redisComponent.getTokenInfo(token);
    }
}
