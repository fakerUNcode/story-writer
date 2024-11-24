package com.easylive.entity.vo;

//各状态的稿件数量的数据封装对象
public class VideoStatusCountVO {
    private Integer auditPassCount;
    private Integer auditFailCount;
    private Integer inProgress;

    public Integer getAuditPassCount() {
        return auditPassCount;
    }

    public void setAuditPassCount(Integer auditPassCount) {
        this.auditPassCount = auditPassCount;
    }

    public Integer getAuditFailCount() {
        return auditFailCount;
    }

    public void setAuditFailCount(Integer auditFailCount) {
        this.auditFailCount = auditFailCount;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

}
