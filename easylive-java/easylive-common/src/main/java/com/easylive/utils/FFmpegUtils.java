package com.easylive.utils;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;

@Slf4j
@Component
public class FFmpegUtils {
    @Resource
    private AppConfig appConfig;

    // 截取视频封面
    public void createImageThumbnail(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            return;
        }
        try {
            // 获取原文件路径
            String fileWithoutSuffix = filePath.substring(0, filePath.lastIndexOf('.'));
            String thumbnailPath = fileWithoutSuffix + Constants.IMAGE_THUMBNAIL_SUFFIX;

            // 拼接 FFmpeg 命令
            String CMD = String.format("ffmpeg -i \"%s\" -vf scale=200:-1 \"%s\" -y", filePath, thumbnailPath);

            // 执行 FFmpeg 命令
            ProcessUtils.executeCommand(CMD, appConfig.getShowFFmpegLog());
        } catch (Exception e) {
            log.error("FFmpeg 生成缩略图失败，文件路径：{}", filePath, e);
        }
    }

    // 获取视频时长方法
    public Integer getVideoInfoDuration(String completeVideo){
        final String CMD_GET_CODE = "ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 \"%s\"";
        String cmd = String.format(CMD_GET_CODE, completeVideo);
        String result = ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());

        // 获取失败返回时长为0，示意视频有错误
        if(result == null || result.trim().isEmpty()){
            log.warn("无法获取视频时长，返回可能为空，文件路径: {}", completeVideo);
            return 0;
        }

        try {
            // 去掉命令输出结果中的换行符和回车符，确保结果是纯数字字符串
            result = result.replace("\n", "").replace("\r", "").trim();
            // 将字符串解析为浮点数后，取其整数部分（向下取整）
            return new BigDecimal(result).intValue();
        } catch (Exception e) {
            log.error("解析视频时长失败，原始返回数据: {}, 文件路径: {}", result, completeVideo, e);
            return 0; // 发生异常兜底返回 0
        }
    }

    // 视频编码格式获取方法
    public String getVideoCodec(String videoFilePath){
        // 【核心优化】：加上 -of default=noprint_wrappers=1:nokey=1 参数，让它只输出结果本身，不带前缀和标签
        final String CMD_GET_CODE = "ffprobe -v error -select_streams v:0 -show_entries stream=codec_name -of default=noprint_wrappers=1:nokey=1 \"%s\"";
        String cmd = String.format(CMD_GET_CODE, videoFilePath);
        String result = ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());

        // 判空防雷：如果执行结果完全为空（例如纯音频文件或文件损坏）
        if (result == null || result.trim().isEmpty()) {
            log.warn("无法获取视频编码格式，可能无视频流或文件损坏，路径: {}", videoFilePath);
            return null; // 返回 null 交给外层业务逻辑去判断
        }

        // 清除多余的换行符和空格后直接返回
        return result.replace("\n", "").replace("\r", "").trim();
    }

    // HEVC(H.265) 转 H.264 操作 (若有用到)
    public void convertHevc2Mp4(String tempFileName, String videoFilePath){
        try {
            String CMD_HEVC_264 = "ffmpeg -i \"%s\" -c:v h264_videotoolbox -b:v 2000k \"%s\" -y";
            String cmd = String.format(CMD_HEVC_264, tempFileName, videoFilePath);
            ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());
        } catch (Exception e) {
            log.error("HEVC转H.264失败，临时文件：{}", tempFileName, e);
            throw new RuntimeException("视频格式转换失败", e);
        }
    }

    // 视频切片生成 TS 和 M3U8 索引 (Mac 加速版)
    public void convertVideo2Ts(File tsFolder, String videoFilePath){
        // 先探测视频真实的编码格式
        String codec = getVideoCodec(videoFilePath);
        String cmdTransfer2Ts;

        // 判断是否为标准 h264
        if ("h264".equalsIgnoreCase(codec)) {
            // 原生 h264，直接 copy 流，极速切片
            cmdTransfer2Ts = "ffmpeg -y -i \"%s\" -vcodec copy -acodec copy -bsf:v h264_mp4toannexb \"%s\"";
            log.info("视频编码为 H.264，采用极速 Copy 模式生成 TS，文件: {}", videoFilePath);
        } else {
            // 非 h264 (如 hevc, vp9) 或者探测失败，强制重编码
            // 【提速秘籍】：采用 Mac 硬件加速 (h264_videotoolbox) 代替 libx264
            cmdTransfer2Ts = "ffmpeg -y -i \"%s\" -c:v h264_videotoolbox -b:v 2000k -c:a aac -bsf:v h264_mp4toannexb \"%s\"";
            log.info("视频编码为 {}，采用 Mac 硬件加速(VideoToolbox)模式生成 TS，文件: {}", codec, videoFilePath);
        }

        final String CMD_CUT_TS = "ffmpeg -i \"%s\" -c copy -map 0 -f segment -segment_list \"%s\" -segment_time 10 %s/%%4d.ts";
        String tsPath = tsFolder.getPath() + "/" + Constants.TS_NAME;

        try {
            // 1. 生成 .ts 巨型临时文件
            String cmd = String.format(cmdTransfer2Ts, videoFilePath, tsPath);
            ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());

            // 2. 生成索引文件 .m3u8 和切片 .ts
            cmd = String.format(CMD_CUT_TS, tsPath, tsFolder.getPath() + "/" + Constants.M3U8_NAME, tsFolder.getPath());
            ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());

        } catch (Exception e) {
            log.error("视频切片转码失败，原文件: {}", videoFilePath, e);
            throw new RuntimeException("生成TS切片失败", e);
        } finally {
            // 确保巨大的 index.ts 临时文件被删除，防止撑爆磁盘
            File indexTs = new File(tsPath);
            if (indexTs.exists()) {
                indexTs.delete();
            }
        }
    }
}