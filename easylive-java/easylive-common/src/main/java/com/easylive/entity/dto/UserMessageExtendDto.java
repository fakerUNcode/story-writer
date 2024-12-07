package com.easylive.entity.dto;

public class UserMessageExtendDto {
    // 消息内容
    private String messageContent;

    // 消息回复内容
    private String messageContentReply;

    // 审核状态
    private Integer auditStatus;

    // 获取消息内容
    public String getMessageContent() {
        return messageContent;
    }

    // 设置消息内容
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    // 获取消息回复内容
    public String getMessageContentReply() {
        return messageContentReply;
    }

    // 设置消息回复内容
    public void setMessageContentReply(String messageContentReply) {
        this.messageContentReply = messageContentReply;
    }

    // 获取审核状态
    public Integer getAuditStatus() {
        return auditStatus;
    }

    // 设置审核状态
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}