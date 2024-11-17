package com.easylive.entity.enums;

public enum UserSexEnum {
    WOMEN(0,"女"),
    MEN(1,"男"),
    SECRECY(2,"保密");

    private Integer type;
    private String desc;

    UserSexEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static UserSexEnum getByType(Integer type){
        for (UserSexEnum item : UserSexEnum.values()){
            if(item.getType().equals(type)){
                return item;
            }
        }
        return null;
    }

    public Integer getType(){return type;}
    public String getDesc(){return desc;}
}
