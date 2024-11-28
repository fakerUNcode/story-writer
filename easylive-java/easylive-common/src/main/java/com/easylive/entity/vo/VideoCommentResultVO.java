package com.easylive.entity.vo;

import com.easylive.entity.po.UserAction;
import com.easylive.entity.po.VideoComment;

import java.util.List;

public class VideoCommentResultVO {

    // 分页结果，包含视频评论的数据
    private PaginationResultVO<VideoComment> commentData;

    // 用户操作列表
    private List<UserAction> userActionList;

    // Getter 和 Setter 方法
    public PaginationResultVO<VideoComment> getCommentData() {
        return commentData;
    }

    public void setCommentData(PaginationResultVO<VideoComment> commentData) {
        this.commentData = commentData;
    }

    public List<UserAction> getUserActionList() {
        return userActionList;
    }

    public void setUserActionList(List<UserAction> userActionList) {
        this.userActionList = userActionList;
    }
}
