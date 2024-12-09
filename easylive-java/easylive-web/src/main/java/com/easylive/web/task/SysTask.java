package com.easylive.web.task;

import com.easylive.service.StatisticsInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//创作中心数据统计任务
@Component
public class SysTask {
    @Resource
    private StatisticsInfoService statisticsInfoService;

    //每天凌晨0点0分0秒执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void statisticsData(){
        statisticsInfoService.statisticsData();
    }
}
