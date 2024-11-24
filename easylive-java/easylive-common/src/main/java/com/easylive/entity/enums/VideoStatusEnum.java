package com.easylive.entity.enums;

//视频状态枚举
public enum VideoStatusEnum {
    STATUS0(  0, "转码中" ),
    STATUS1(  1, "转码失败" ),
    STATUS2(  2, "待审核" ),
    STATUS3(  3, "审核成功" ),
    STATUS4(  4, "审核不通过" );

    private Integer status;
    private String desc;

    VideoStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() { return status; }
    public String getDesc() { return desc; }

    public static VideoStatusEnum getByStatus(Integer status) {
        for (VideoStatusEnum statusEnum : VideoStatusEnum.values()) {
            if (statusEnum.getStatus().equals(status)) {
                return statusEnum;
            }
        }
        return null;
    }

}
