package com.easylive.web.controller;

import com.easylive.entity.constants.Constants;
import com.easylive.entity.po.VideoDanmu;
import com.easylive.entity.po.VideoInfo;
import com.easylive.entity.query.VideoDanmuQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.VideoDanmuService;
import com.easylive.service.VideoInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

/**
 * 视频弹幕 Controller
 */
@RestController("videoDanmuController")
@RequestMapping("/danmu")
public class VideoDanmuController extends ABaseController{
    @Resource
    private VideoDanmuService videoDanmuService;
    @Resource
    private VideoInfoService videoInfoService;

    //弹幕发布接口,传入参数：视频id,文件id，弹幕内容，弹幕位置，弹幕颜色，弹幕在视频中的出现时间点
    @RequestMapping("/postDanmu")
    public ResponseVO postDanmu(@NotEmpty String videoId, @NotEmpty String fileId,
                               @NotEmpty @Size(max=200) String text, @NotNull Integer mode,
                               @NotNull String color,@NotNull Integer time){
        VideoDanmu videoDanmu = new VideoDanmu();
        videoDanmu.setVideoId(videoId);           // 设置视频ID
        videoDanmu.setFileId(fileId);             // 设置文件ID
        videoDanmu.setText(text);                 // 设置弹幕的文本内容
        videoDanmu.setMode(mode);                 // 设置弹幕的模式（可能是滚动、顶部、底部等）
        videoDanmu.setColor(color);               // 设置弹幕的颜色
        videoDanmu.setTime(time);                 // 设置弹幕的时间点（视频中出现的时间）
        videoDanmu.setUserId(getTokenUserInfoDto().getUserId()); // 设置用户ID，可能通过一个用户认证Token获取
        videoDanmu.setPostTime(new Date());       // 设置弹幕的发布时间为当前时间

        videoDanmuService.saveVideoDanmu(videoDanmu);
        return getSuccessResponseVO(null);
    }
    @RequestMapping("/loadDanmu")
    public ResponseVO loadDanmu(@NotEmpty String fileId,@NotEmpty String videoId ){
        VideoInfo videoInfo = videoInfoService.getVideoInfoByVideoId(videoId);
        if(videoInfo.getInteraction()!=null && videoInfo.getInteraction().contains(Constants.ONE.toString())){
            return getSuccessResponseVO(new ArrayList<>());
        }
        //设置弹幕查询条件为：与视频文件关联，且按弹幕id升序排列
        VideoDanmuQuery videoDanmuQuery = new VideoDanmuQuery();
        videoDanmuQuery.setFileId(fileId);
        videoDanmuQuery.setOrderBy("danmu_id asc");
        return getSuccessResponseVO(videoDanmuService.findListByParam(videoDanmuQuery));
    }
}