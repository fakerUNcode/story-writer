package com.easylive.web.controller;

import com.easylive.entity.po.StatisticsInfo;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.mappers.StatisticsInfoMapper;
import com.easylive.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 本地测试专用：一键生成历史统计数据
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestStatisticsController extends ABaseController {

    @Resource
    private StatisticsInfoMapper<StatisticsInfo, ?> statisticsInfoMapper;

    /**
     * 为 GUMI coder (8990397154) 专属生成过去7天的逼真测试数据
     */
    @RequestMapping("/initGumiData")
    public ResponseVO initGumiData() {
        // 从你的历史日志中提取到的 GUMI coder 的真实用户ID
        String targetUserId = "8990397154";

        // 获取过去7天的日期列表 (格式: yyyy-MM-dd)
        List<String> dateList = DateUtil.getBeforeDaysDate(7);
        Random random = new Random();

        // 假设的统计数据类型 (请根据你的 StatisticsTypeEnum 实际枚举值核对)
        // 常见的对应关系：0:播放量, 1:粉丝数(不一定在流水表), 2:点赞, 3:评论, 4:弹幕, 5:硬币, 6:收藏
        int[] dataTypes = {0, 2, 3, 4, 5, 6};

        int insertCount = 0;

        for (String date : dateList) {
            for (int dataType : dataTypes) {
                StatisticsInfo info = new StatisticsInfo();
                info.setStatisticsDate(date);
                info.setDataType(dataType);

                // 【核心】：精确绑定到 GUMI coder 账号下
                info.setUserId(targetUserId);

                // 算法：生成逼真的起伏数据，让前端的折线图非常好看
                int count = 0;
                if (dataType == 0) {
                    // 播放量基数比较大：2000 ~ 7000 波动
                    count = 2000 + random.nextInt(5000);
                } else if (dataType == 2 || dataType == 5 || dataType == 6) {
                    // 赞、币、收藏属于强互动：100 ~ 600 波动
                    count = 100 + random.nextInt(500);
                } else {
                    // 弹幕、评论属于弱互动：50 ~ 250 波动
                    count = 50 + random.nextInt(200);
                }
                info.setStatisticsCount(count);

                try {
                    // 执行插入
                    // 如果实体类或者表结构有主键/唯一索引限制，这里可以考虑使用 insertOrUpdate
                    statisticsInfoMapper.insert(info);
                    insertCount++;
                } catch (Exception e) {
                    log.error("插入模拟数据失败，日期:{}, 类型:{}", date, dataType, e);
                }
            }
        }

        return getSuccessResponseVO("成功为 GUMI coder (ID: 8990397154) 生成了 " + insertCount + " 条历史模拟数据！快去刷新管理端图表吧！");
    }
}