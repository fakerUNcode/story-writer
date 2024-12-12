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
public class VideoController extends ABaseController{
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
        // 优先从正式表查询
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);
        if (videoInfoFile == null) {
            // 正式表中未找到，再从审核表查询
            videoInfoFile = videoInfoFilePostService.getVideoInfoFileByFileId(fileId);
        }

        if (videoInfoFile == null || videoInfoFile.getFilePath() == null) {
            log.error("未找到视频文件信息，fileId: {}", fileId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String filePath = videoInfoFile.getFilePath();
        readFile(response, filePath + "/" + Constants.M3U8_NAME);

        // 更新视频的播放信息
        VideoPlayInfoDto videoPlayInfoDto = new VideoPlayInfoDto();
        videoPlayInfoDto.setVideoId(videoInfoFile.getVideoId());
        videoPlayInfoDto.setFileIndex(videoInfoFile.getFileIndex());
        redisComponent.addVideoPlay(videoPlayInfoDto);
    }

    // 查询视频资源片段（已审核或未审核）
    @RequestMapping("/videoResource/{fileId}/{ts}")
    public void videoResourceTs(HttpServletResponse response, @PathVariable @NotEmpty String fileId, @PathVariable @NotEmpty String ts) {
        // 优先从正式表查询
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);
        if (videoInfoFile == null) {
            // 正式表中未找到，再从审核表查询
            videoInfoFile = videoInfoFilePostService.getVideoInfoFileByFileId(fileId);
        }

        if (videoInfoFile == null || videoInfoFile.getFilePath() == null) {
            log.error("未找到视频文件信息，fileId: {}", fileId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String filePath = videoInfoFile.getFilePath();
        readFile(response, filePath + "/" + ts);
    }

}
