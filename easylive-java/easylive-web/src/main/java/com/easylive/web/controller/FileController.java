package com.easylive.web.controller;

import com.easylive.component.RedisComponent;
import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.dto.VideoPlayInfoDto;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.po.VideoInfoFile;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.VideoInfoFileService;
import com.easylive.utils.DateUtil;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/*上传视频以及相关文件Controller*/
@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class FileController extends ABaseController {
    @Resource
    private AppConfig appConfig;
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private FFmpegUtils fFmpegUtils;
    @Resource
    VideoInfoFileService videoInfoFileService;

    @RequestMapping("/getResource")
    public void getResource(HttpServletResponse response, @NotNull String sourceName) {
        if (!StringTools.pathIsOk(sourceName)) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        String suffix = StringTools.getFileSuffix(sourceName);
        //拼接 Content-Type，告知客户端文件是图片，格式如 image/jpg
        response.setContentType("image/" + suffix.replace(".", ""));
        //Cache-Control 设置图片缓存时间为 30 天（2592000 秒），提高加载性能
        response.setHeader("Cache-Control", "max-age=2592000");
        readFile(response, sourceName);
    }

    //将指定路径的文件内容以流的形式返回给客户端
    protected void readFile(HttpServletResponse response, String filePath) {
        File file = new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + filePath);
        if (!file.exists()) {
            return;
        }
        //使用 try-with-resources 自动关闭资源（FileInputStream 和 OutputStream）
        try (OutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(file)) {
            //每次读取 1024 字节，避免大文件一次性加载到内存导致性能问题
            byte[] byteData = new byte[1024];
            int len = 0;
            while ((len = in.read(byteData)) != -1) {
                out.write(byteData, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error("读取文件异常", e);
        }
    }

    //视频预上传处理interface
    @RequestMapping("preUploadVideo")
    public ResponseVO preUploadVideo(@NotEmpty String fileName, @NotNull Integer chunks) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        String uploadId = redisComponent.savePreVideoFileInfo(tokenUserInfoDto.getUserId(), fileName, chunks);
        return getSuccessResponseVO(uploadId);
    }

    //视频上传处理interface
    @RequestMapping("uploadVideo")
    public ResponseVO uploadVideo(@NotNull MultipartFile chunkFile, @NotNull Integer chunkIndex, @NotEmpty String uploadId) throws IOException {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UploadingFileDto fileDto = redisComponent.getUploadVideoFile(tokenUserInfoDto.getUserId(), uploadId);
        if (fileDto == null) {
            throw new BusinessException("文件不存在，请重新上传！");
        }
        SysSettingDto sysSettingDto = redisComponent.getSysSettingDto();

        if (fileDto.getFileSize() > sysSettingDto.getVideoSize() * Constants.MB_SIZE) {
            throw new BusinessException("文件超过大小限制！");
        }
        /*
        fileDto.getChunkIndex(): 表示当前已经成功上传的最大分片索引。
        fileDto.getChunks(): 表示总分片数。
        检查确保：当前上传的分块是上一个分块的下一块，按分块顺序依次上传；且文件上传的分块数不能大于可分块的最大数
        */
        if ((chunkIndex - 1) > fileDto.getChunkIndex() || chunkIndex > fileDto.getChunks() - 1) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }

        String folder = appConfig.getProjectFolder() + Constants.FILE_FOLDER + Constants.FILE_FOLDER_TEMP + fileDto.getFilePath();
        File targetFile = new File(folder + "/" + chunkIndex);
        File folderFile = new File(folder);
        //再次效验目录是否创建
        if (!folderFile.exists()) {
            boolean created = folderFile.mkdirs();
            log.debug("Created folder: {}, success: {}", folder, created);
            if (!created) {
                throw new IOException("Failed to create directory: " + folder);
            }
        }
        chunkFile.transferTo(targetFile);
        fileDto.setChunkIndex(chunkIndex);
        //fileDto的FileSize为已上传的总大小，之前的总大小＋要上传的分块大小 = 上传该分块后已经上传的总大小
        fileDto.setFileSize(fileDto.getFileSize() + chunkFile.getSize());
        redisComponent.updateVideoFileInfo(tokenUserInfoDto.getUserId(), fileDto);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("delUploadVideo")
    public ResponseVO delUploadVideo(@NotEmpty String uploadId) throws IOException {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfoDto();
        UploadingFileDto fileDto = redisComponent.getUploadVideoFile(tokenUserInfoDto.getUserId(), uploadId);
        if (fileDto == null) {
            throw new BusinessException("文件不存在！请重新上传");
        }
        redisComponent.delVideoFileInfo(tokenUserInfoDto.getUserId(), uploadId);
        FileUtils.deleteDirectory(new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + Constants.FILE_FOLDER_TEMP + fileDto.getFilePath()));
        return getSuccessResponseVO(uploadId);
    }


    @RequestMapping("uploadImage")
    public ResponseVO uploadImage(@NotNull MultipartFile file, @NotNull Boolean createThumbnail) throws IOException {
        String day = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMMDD.getPattern());
        String folder = appConfig.getProjectFolder() + Constants.FILE_FOLDER + Constants.FILE_COVER + day;
        File folderFile = new File(folder);
        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String fileSuffix = StringTools.getFileSuffix(fileName);
        String realFileName = StringTools.getRandomString(Constants.LENGTH_30) + fileSuffix;
        String filePath = folder + "/" + realFileName;
        file.transferTo(new File(filePath));

        if (createThumbnail) {
            fFmpegUtils.createImageThumbnail(filePath);
        }
        return getSuccessResponseVO(Constants.FILE_COVER + day + "/" + realFileName);
    }

    @RequestMapping("/videoResource/{fileId}")
    public void videoResource(HttpServletResponse response, @PathVariable @NotEmpty String fileId) {
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);
        String filePath = videoInfoFile.getFilePath();
        readFile(response, filePath + "/" + Constants.M3U8_NAME);
        //更新视频的播放信息等
        VideoPlayInfoDto videoPlayInfoDto = new VideoPlayInfoDto();
        videoPlayInfoDto.setVideoId(videoInfoFile.getVideoId());

        videoPlayInfoDto.setFileIndex(videoInfoFile.getFileIndex());

        TokenUserInfoDto tokenUserInfoDto = getTokenInfoFromCookie();
        //只有登录的用户的行为才会更新视频播放信息
        if(tokenUserInfoDto!=null){
            videoPlayInfoDto.setUserId(tokenUserInfoDto.getUserId());
        }
        redisComponent.addVideoPlay(videoPlayInfoDto);
    }
    @RequestMapping("/videoResource/{fileId}/{ts}")
    public void videoResourceTs(HttpServletResponse response, @PathVariable @NotEmpty String fileId,@PathVariable @NotEmpty String ts) {
        VideoInfoFile videoInfoFile = videoInfoFileService.getVideoInfoFileByFileId(fileId);
        String filePath = videoInfoFile.getFilePath();
        readFile(response, filePath + "/" + ts);
    }
}

