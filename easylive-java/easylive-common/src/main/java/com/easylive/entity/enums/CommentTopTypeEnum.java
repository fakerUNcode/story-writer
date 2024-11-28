package com.easylive.entity.enums;

public enum CommentTopTypeEnum {
    // 定义枚举常量
    NO_TOP(0, "未置顶"),
    TOP(1, "已置顶");

    // 定义枚举字段
    private Integer type;
    private String desc;

    // 构造方法
    CommentTopTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    // 根据类型值获取对应的枚举对象
    public static CommentTopTypeEnum getByType(Integer type) {
        for (CommentTopTypeEnum item : CommentTopTypeEnum.values()) {
            if (item.getType() != null && item.getType().equals(type)) {
                return item;
            }
        }
        return null;
    }

    // 获取枚举的类型值
    public Integer getType() {
        return type;
    }

    // 获取枚举的描述信息
    public String getDesc() {
        return desc;
    }
}
