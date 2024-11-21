package com.easylive.utils;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FFmpegUtils {
    @Resource
    private AppConfig appConfig;

    public void createImageThumbnail(String filePath) {
        // 获取原文件路径
        String fileWithoutSuffix = filePath.substring(0, filePath.lastIndexOf('.'));
        String thumbnailPath = fileWithoutSuffix + Constants.IMAGE_THUMBNAIL_SUFFIX;

        // 拼接 FFmpeg 命令
        String CMD = String.format("ffmpeg -i \"%s\" -vf scale=200:-1 \"%s\"", filePath, thumbnailPath);

        // 执行 FFmpeg 命令
        try {
            ProcessUtils.executeCommand(CMD, appConfig.getShowFFmegLog());
        } catch (Exception e) {
            throw new RuntimeException("FFmpeg 生成缩略图失败：" + CMD, e);
        }
    }

}
