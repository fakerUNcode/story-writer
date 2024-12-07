package com.easylive.entity.enums;

public enum MessageReadTypeEnum {
    // 定义两个枚举常量：未读和已读状态
    NO_READ( 0,  "未读"),
    READ( 1,  "已读");

    // 枚举类的属性
    private Integer type;    // 状态码
    private String desc;     // 状态描述

    // 构造函数
    MessageReadTypeEnum(Integer status, String desc) {
        this.type = status;
        this.desc = desc;
    }

    // 获取状态码
    public Integer getType() {
        return type;
    }

    // 获取状态描述
    public String getDesc() {
        return desc;
    }

    // 根据状态码查找对应的枚举实例
    public static MessageReadTypeEnum getByStatus(Integer status) {
        // 遍历所有枚举值
        for (MessageReadTypeEnum statusEnum : MessageReadTypeEnum.values()) {
            // 如果找到匹配的状态码，返回对应的枚举实例
            if (statusEnum.getType().equals(status)) {
                return statusEnum;
            }
        }
        // 如果没找到匹配的状态码，返回null
        return null;
    }
}