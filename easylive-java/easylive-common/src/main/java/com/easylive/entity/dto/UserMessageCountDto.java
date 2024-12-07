package com.easylive.entity.dto;

public class UserMessageCountDto {
    // 消息类型，用于区分不同种类的消息
    public Integer messageType;

    // 消息数量，用于记录特定类型消息的计数
    private Integer messageCount;

    // 获取消息类型
    public Integer getMessageType() {
        return messageType;
    }

    // 设置消息类型
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    // 获取消息数量
    public Integer getMessageCount() {
        return messageCount;
    }

    // 设置消息数量
    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}