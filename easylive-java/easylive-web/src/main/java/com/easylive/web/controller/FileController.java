package com.easylive.web.controller;

import com.easylive.entity.config.AppConfig;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class FileController extends ABaseController{
    @Resource
    private AppConfig appConfig;

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

