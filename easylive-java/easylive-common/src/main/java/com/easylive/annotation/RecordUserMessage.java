package com.easylive.annotation;

import com.easylive.entity.enums.MessageTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RecordUserMessage {
    MessageTypeEnum messageType() default MessageTypeEnum.SYS; // 默认值
}