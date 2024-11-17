package com.easylive.entity.enums;


public enum DateTimePatternEnum {
    //定义了两种日期格式：yyyy-MM-dd HH:mm:ss 和 yyyy-MM-dd。
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYY_MM_DD("yyyy-MM-dd");

    private String pattern;

    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
