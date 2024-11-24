package com.easylive.utils;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;

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
            ProcessUtils.executeCommand(CMD, appConfig.getShowFFmpegLog());
        } catch (Exception e) {
            throw new RuntimeException("FFmpeg 生成缩略图失败：" + CMD, e);
        }
    }

    //获取视频时长方法
    public Integer getVideoInfoDuration(String completeVideo){
        final String CMD_GET_CODE = "ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 \"%s\"";
        String cmd = String.format(CMD_GET_CODE,completeVideo);
        String result = ProcessUtils.executeCommand(cmd,appConfig.getShowFFmpegLog());
        //获取失败返回时长为0，示意视频有错误
        if(StringTools.isEmpty(result)){
            return 0;
        }
        //result结果为字符串（如：5.360000），需要进一步解析为数
        /*result.replace("\n", "")：去掉命令输出结果中的换行符，确保结果是纯数字字符串。
        new BigDecimal(result).intValue()：将字符串解析为浮点数后，取其整数部分（向下取整）*/
        result = result.replace("\n","");
        return new BigDecimal(result).intValue();
    }

    //视频编码格式获取方法
    public String getVideoCodec(String videoFilePath){
        final String CMD_GET_CODE = "ffprobe -v error -select_streams v:0 -show_entries stream=codec_name \"%s\"";
        String cmd = String.format(CMD_GET_CODE, videoFilePath);
        String result = ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());
        result = result.replace("\n", "");
        //提取 =后的、[前的内容
        result = result.substring(result.indexOf("=") + 1);
        String codec = result.substring(0, result.indexOf("["));
        return codec;
    }

    //转码操作
    public void convertHevc2Mp4(String tempFileName, String videoFilePath){
        String CMD_HEVC_264 = "ffmpeg -i \"%s\" -c:v libx264 -crf 20 \"%s\" -y";
        String cmd = String.format(CMD_HEVC_264, tempFileName, videoFilePath);
        ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());
    }

    public void convertVideo2Ts(File tsFolder, String videoFilePath){
        final String CMD_TRANSFER_2TS = "ffmpeg -y -i \"%s\" -vcodec copy -acodec copy -bsf:v h264_mp4toannexb \"%s\"";
        final String CMD_CUT_TS = "ffmpeg -i \"%s\" -c copy -map 0 -f segment -segment_list \"%s\" -segment_time 10 %s/%%4d.ts";
        String tsPath = tsFolder + "/" + Constants.TS_NAME;
        // 生成 .ts 文件
        String cmd = String.format(CMD_TRANSFER_2TS, videoFilePath, tsPath);
        ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());
        // 生成索引文件 .m3u8 和切片 .ts
        cmd = String.format(CMD_CUT_TS, tsPath, tsFolder.getPath() + "/" + Constants.M3U8_NAME, tsFolder.getPath());
        ProcessUtils.executeCommand(cmd, appConfig.getShowFFmpegLog());
        // 删除 index.ts
        new File(tsPath).delete();
    }

}
