package com.easylive.component;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.SysSettingDto;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.dto.UploadingFileDto;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.entity.po.CategoryInfo;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.redis.RedisUtils;
import com.easylive.utils.DateUtil;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.easylive.entity.constants.Constants.LENGTH_15;
import static com.easylive.entity.constants.Constants.REDIS_KEY_EXPIRES_ONE_MIN;


@Component
public class RedisComponent {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private AppConfig appConfig;

    public String saveCheckCode(String code){
        String checkCodeKey = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey, code, REDIS_KEY_EXPIRES_ONE_MIN*10);
        return checkCodeKey;
    }

    public String getCheckCode(String checkCodeKey){
        return (String) redisUtils.get(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    //刷新验证码
    public void cleanCheckCode(String checkCodeKey){
        redisUtils.delete(Constants.REDIS_KEY_CHECK_CODE+checkCodeKey);
    }

    public void saveTokenInfo(TokenUserInfoDto tokenUserInfoDto){
        String token = UUID.randomUUID().toString();
        //用户登录的token有效期为7天，7天后需要重新登录
        tokenUserInfoDto.setExpireAt(System.currentTimeMillis() + Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
        tokenUserInfoDto.setToken(token);
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_WEB + token, tokenUserInfoDto,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }



    public void cleanToken(String token){
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_WEB + token);
    }

    public TokenUserInfoDto getTokenInfo(String token) {
        String redisKey = Constants.REDIS_KEY_TOKEN_WEB + token;
        TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(redisKey);
        return tokenUserInfoDto;
    }


    public String getTokenInfo4Admin(String token){
        return (String) redisUtils.get(Constants.REDIS_KEY_TOKEN_ADMIN+token);
    }

    public String saveTokenInfo4Admin(String account){
        String token = UUID.randomUUID().toString();
        redisUtils.setex(Constants.REDIS_KEY_TOKEN_ADMIN+token,account,Constants.REDIS_KEY_EXPIRES_ONE_DAY);
        return token;
    }

    public void cleanToken4Admin(String token){
        redisUtils.delete(Constants.REDIS_KEY_TOKEN_ADMIN + token);
    }

    //将CategoryInfo列表存入Redis缓存，以便后续使用
    public void saveCategoryList(List<CategoryInfo> categoryInfoList){
        redisUtils.set(Constants.REDIS_KEY_CATEGORY_LIST, categoryInfoList);
    }

    public List<CategoryInfo> getCategoryList(){
       return  (List<CategoryInfo>) redisUtils.get(Constants.REDIS_KEY_CATEGORY_LIST);
    }

    //保存视频上传前的数据 返回视频上传的唯一id
    public String savePreVideoFileInfo(String userId, String filename, Integer chunks){
        String uploadId = StringTools.getRandomString(LENGTH_15);
        UploadingFileDto fileDto = new UploadingFileDto();
        fileDto.setChunks(chunks);
        fileDto.setFileName(filename);
        fileDto.setUploadId(uploadId);
        fileDto.setChunkIndex(0);

        String day = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMMDD.getPattern());
        String filePath = day + "/" + userId + uploadId;
        //还没有确定提交投稿的视频暂存到临时目录 避免资源浪费
        String folder = appConfig.getProjectFolder() + Constants.FILE_FOLDER + Constants.FILE_FOLDER_TEMP + filePath;
        File folderFile = new File(folder);
        //目录不存在则创建
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        fileDto.setFilePath(filePath);
        //最大上传有效期为1天
        redisUtils.setex(Constants.REDIS_KEY_UPLOADING_FILE + userId + uploadId, fileDto, Constants.REDIS_KEY_EXPIRES_ONE_DAY);
        return uploadId;
    }

    public UploadingFileDto getUploadVideoFile(String userId, String uploadId) {
        UploadingFileDto uploadingFileDto =  (UploadingFileDto) redisUtils.get(Constants.REDIS_KEY_UPLOADING_FILE + userId + uploadId);
        return uploadingFileDto;
    }

    public SysSettingDto getSysSettingDto(){
        SysSettingDto sysSettingDto = (SysSettingDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);
        //未取到系统设置传输对象则新建一个，使用该对象的默认值
        if(sysSettingDto==null){
            sysSettingDto = new SysSettingDto();
        }
        return sysSettingDto;
    }

    public void updateVideoFileInfo(String userId, UploadingFileDto fileDto){
        redisUtils.setex(Constants.REDIS_KEY_UPLOADING_FILE + userId+ fileDto.getUploadId(), fileDto, Constants.REDIS_KEY_EXPIRES_ONE_DAY);
    }

    public void delVideoFileInfo(String userId, String uploadId){
        redisUtils.delete(Constants.REDIS_KEY_UPLOADING_FILE + userId+ uploadId);
    }

    public void addFile2DelQueue(String videoId, List<String> filePathList){
        //左侧加入消息队列
        redisUtils.lpushAll(Constants.REDIS_KEY_FILE_DEL + videoId,filePathList,Constants.REDIS_KEY_EXPIRES_ONE_DAY*7);
    }
    public void addFile2TransferQueue(List<VideoInfoFilePost> addFileList){
        //左侧加入消息队列
        redisUtils.lpushAll(Constants.REDIS_KEY_QUEUE_TRANSFER, addFileList, 0);
    }

    public void addFile2TransferQueue4single(VideoInfoFilePost videoInfoFilePost){
        redisUtils.lpush(Constants.REDIS_KEY_QUEUE_TRANSFER,videoInfoFilePost,0L);
    }

    public VideoInfoFilePost getFileFromTransferQueue(){
        //右侧推出消息队列
       return (VideoInfoFilePost) redisUtils.rpop(Constants.REDIS_KEY_QUEUE_TRANSFER);
    }
}
