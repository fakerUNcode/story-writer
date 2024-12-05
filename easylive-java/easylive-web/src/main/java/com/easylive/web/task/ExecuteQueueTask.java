package com.easylive.web.task;

import com.easylive.component.EsSearchComponent;
import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.dto.VideoPlayInfoDto;
import com.easylive.entity.enums.SearchOrderTypeEnum;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.service.VideoInfoPostService;
import com.easylive.service.VideoInfoService;
import com.easylive.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ExecuteQueueTask {
    //设置线程数为2
    private ExecutorService executorService = Executors.newFixedThreadPool(Constants.LENGTH_2);
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private VideoInfoPostService videoInfoPostService;
    @Resource
    private VideoInfoService videoInfoService;
    @Resource
    private EsSearchComponent esSearchComponent;

    /* 在 Spring 容器启动时自动执行。
    该方法开启了一个独立线程（通过 ExecutorService），持续从 Redis 消息队列中消费需要转码的视频文件任务。*/
    @PostConstruct
    public void consumeTransferFileQueue(){
        executorService.execute(()->{
            while (true){
                try{
                    VideoInfoFilePost videoInfoFile = redisComponent.getFileFromTransferQueue();
                    //消息队列取不到VideoInfoFilePost说明队列空了，停顿一会再取
                    if(videoInfoFile == null){
                        Thread.sleep(1500);
                        continue;
                    }
                    videoInfoPostService.transferVideoFile(videoInfoFile);
                }catch (Exception e){
                    log.error("获取转码文件队列信息失败！",e);
                }
            }
        });
    }

    //消费视频播放队列
    @PostConstruct
    public void consumeVideoPlayQueue(){
        executorService.execute(()->{
            while (true){
                try{
                    VideoPlayInfoDto videoPlayInfoDto = redisComponent.getVideoPlayFromVideoPlayQueue();
                    //消息队列取不到VideoInfoFilePost说明队列空了，停顿一会再取
                    if(videoPlayInfoDto == null){
                        Thread.sleep(1500);
                        continue;
                    }
                    //更新播放数量
                    videoInfoService.addReadCount(videoPlayInfoDto.getVideoId());
                    if(!StringTools.isEmpty(videoPlayInfoDto.getUserId())){
                        //TODO 记录播放历史
                    }
                    // 按天记录视频播放
                    redisComponent.recordVideoPlayCount(videoPlayInfoDto.getVideoId());
                    //更新es播放数量
                    esSearchComponent.updateDocCount(videoPlayInfoDto.getVideoId(), SearchOrderTypeEnum.VIDEO_PLAY.getField(), 1);
                }catch (Exception e){
                    log.error("获取视频播放文件队列信息失败！",e);
                }
            }
        });
    }

}
