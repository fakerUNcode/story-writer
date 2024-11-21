package com.easylive.admin.controller;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.DateTimePatternEnum;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.utils.DateUtil;
import com.easylive.utils.FFmpegUtils;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class FileController extends ABaseController{
    @Resource
    private AppConfig appConfig;

    @Resource
    private FFmpegUtils fFmpegUtils;

    @RequestMapping("/uploadImage")
    public ResponseVO uploadImage(@NotNull MultipartFile file, @NotNull Boolean createThumbnail) throws IOException {
        String month = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMM.getPattern());
        //存入目录：easylive/admin/file/cover/月份 （由于该接口仅管理员使用，因此我们去除temp目录的过渡，直接上传cover
        String folder = appConfig.getProjectFolder()+ Constants.FILE_FOLDER + Constants.FILE_COVER+month;
        File folderFile = new File(folder);
        //目录不存在需创建
        if(!folderFile.exists()){
            folderFile.mkdirs();
        }
        //获取上传文件的原始名称，例如 image.jpg
        String fileName = file.getOriginalFilename();
        //找到文件名中最后一个 . 的位置，提取文件后缀 .xxx
        String fileSuffix = StringTools.getFileSuffix(fileName);
        //完整的文件名是随机字符串加上文件后缀，例如 abc123xyz456789101112131...(30位随机字符）.jpg
        String realFileName = StringTools.getRandomString(Constants.LENGTH_30) + fileSuffix;

        //文件的完整存储路径:easylive/admin/file/cover/abc123xyz456789101112131...(30位随机字符）.jpg
        String filePath = folder + "/" + realFileName;
        file.transferTo(new File(filePath));
        //生成缩略图
        if(createThumbnail){
            fFmpegUtils.createImageThumbnail(filePath);
        }
        return getSuccessResponseVO(Constants.FILE_COVER + month + "/" + realFileName);
    }

    @RequestMapping("/getResource")
    public void getResource(HttpServletResponse response, @NotNull String sourceName)  {
        if(!StringTools.pathIsOk(sourceName)){
            throw  new BusinessException(ResponseCodeEnum.CODE_600);
        }
        String suffix = StringTools.getFileSuffix(sourceName);
        //拼接 Content-Type，告知客户端文件是图片，格式如 image/jpg
        response.setContentType("image/" + suffix.replace(".", ""));
        //Cache-Control 设置图片缓存时间为 30 天（2592000 秒），提高加载性能
        response.setHeader("Cache-Control","max-age=2592000");
        readFile(response,sourceName);
    }

    //将指定路径的文件内容以流的形式返回给客户端
    protected void readFile(HttpServletResponse response, String filePath){
        File file = new File(appConfig.getProjectFolder() + Constants.FILE_FOLDER + filePath);
        if(!file.exists()){
            return;
        }
        //使用 try-with-resources 自动关闭资源（FileInputStream 和 OutputStream）
        try (OutputStream out = response.getOutputStream(); FileInputStream in = new FileInputStream(file)){
            //每次读取 1024 字节，避免大文件一次性加载到内存导致性能问题
            byte[] byteData = new byte[1024];
            int len = 0;
            while ((len = in.read(byteData)) != -1){
                out.write(byteData,0,len);
            }
            out.flush();
        }catch (Exception e){
            log.error("读取文件异常",e);
        }
    }
}

