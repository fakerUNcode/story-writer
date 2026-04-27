package com.easylive.admin.controller;

import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.VideoPlayInfoDto;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.service.VideoInfoFilePostService;
import com.easylive.service.VideoInfoFileService;
import com.easylive.utils.FFmpegUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class VideoController extends ABaseController {
    @Resource
    private AppConfig appConfig;
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private FFmpegUtils fFmpegUtils;
    @Resource
    private VideoInfoFileService videoInfoFileService;
    @Resource
    private VideoInfoFilePostService videoInfoFilePostService;

    // 将指定路径的文件内容以流的形式返回给客户端
    protected void readFile(HttpServletResponse response, String filePath) {
        File file = new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + filePath);
        if (!file.exists()) {
            log.error("文件不存在: {}", file.getAbsolutePath());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try (OutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(file)) {
            byte[] byteData = new byte[1024];
            int len;
            while ((len = in.read(byteData)) != -1) {
                out.write(byteData, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error("读取文件异常", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // 查询视频资源（已审核或未审核）
    @RequestMapping("/videoResource/{fileId}")
    public void videoResource(HttpServletResponse response, @PathVariable @NotEmpty String fileId) {
        boolean isAuditPreview = false;

        // 1. 优先从正式表查询
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);

        // 2. 正式表中未找到，再从审核表查询
        if (videoInfoFile == null) {
            // 【妥协修复】：因为底层的 PostService 错误地返回了 VideoInfoFile，所以这里直接用 VideoInfoFile 接
            videoInfoFile = videoInfoFilePostService.getVideoInfoFileByFileId(fileId);
            isAuditPreview = true; // 标记为正在审核的稿件
        }

        if (videoInfoFile == null || videoInfoFile.getFilePath() == null) {
            log.error("未找到视频文件信息，fileId: {}", fileId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 【核心修复 1】：强制设置 HLS M3U8 的 Content-Type 和防缓存头（解决预览时好时坏）
        response.setContentType("application/x-mpegURL");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        readFile(response, videoInfoFile.getFilePath() + "/" + Constants.M3U8_NAME);

        // 【核心修复 2】：只有正式发布的视频才记录播放量，防止审核时刷数据或报错
        if (!isAuditPreview) {
            try {
                VideoPlayInfoDto videoPlayInfoDto = new VideoPlayInfoDto();
                videoPlayInfoDto.setVideoId(videoInfoFile.getVideoId());
                videoPlayInfoDto.setFileIndex(videoInfoFile.getFileIndex());
                redisComponent.addVideoPlay(videoPlayInfoDto);
            } catch (Exception e) {
                log.error("记录视频播放量异常, videoId: {}", videoInfoFile.getVideoId(), e);
            }
        }
    }

    // 查询视频资源片段（已审核或未审核）
    @RequestMapping("/videoResource/{fileId}/{ts}")
    public void videoResourceTs(HttpServletResponse response, @PathVariable @NotEmpty String fileId, @PathVariable @NotEmpty String ts) {
        // 优先从正式表查询
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);

        // 正式表中未找到，再从审核表查询
        if (videoInfoFile == null) {
            videoInfoFile = videoInfoFilePostService.getVideoInfoFileByFileId(fileId);
        }

        if (videoInfoFile == null || videoInfoFile.getFilePath() == null) {
            log.error("未找到视频片段信息，fileId: {}", fileId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 【核心修复 3】：强制设置 TS 切片专用的 Content-Type
        response.setContentType("video/MP2T");
        readFile(response, videoInfoFile.getFilePath() + "/" + ts);
    }
}