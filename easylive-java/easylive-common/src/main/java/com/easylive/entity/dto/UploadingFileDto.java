package com.easylive.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

//当前已上传的视频的部分 数据传输对象

//注解：用于忽略 JSON 中未知的属性，确保在反序列化 JSON 数据时，即使传递的 JSON 包含未定义的字段，也不会报错。
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadingFileDto implements Serializable {

    private static final long serialVersionUID = 844272933084899283L;
    private String uploadId;
    private String fileName;
    //视频切片索引
    private Integer chunkIndex;
    //切片数
    private Integer chunks;
    private Long fileSize = 0L;
    private String filePath;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
