package com.easylive.admin.controller;

import com.easylive.entity.enums.StatisticsTypeEnum;
import com.easylive.entity.po.StatisticsInfo;
import com.easylive.entity.query.StatisticsInfoQuery;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.service.StatisticsInfoService;
import com.easylive.service.UserInfoService;
import com.easylive.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*用户创作中心数据统计 Controller*/
@RestController
@RequestMapping("/index")
@Validated
@Slf4j
public class IndexController extends ABaseController{

    @Resource
    private StatisticsInfoService statisticsInfoService;
    @Resource
    private UserInfoService userInfoService;

    /*获取统计数据*/
    @RequestMapping("/getActualTimeStatisticsInfo")
    public ResponseVO getActualTimeStatisticsInfo() {
        /*前一天的数据*/
        String preDate = DateUtil.getBeforeDayDate(1);

        StatisticsInfoQuery param = new StatisticsInfoQuery();
        param.setStatisticsDate(preDate);

        List<StatisticsInfo> preDayData = statisticsInfoService.findListTotalInfoByParam(param);

        Integer userCount = userInfoService.findCountByParam(new UserInfoQuery());
        preDayData.forEach(item->{
            if(StatisticsTypeEnum.FANS.getType().equals(item.getDataType())){
                item.setStatisticsCount(userCount);
            }
        });

        Map<Integer,Integer> preDayDataMap = preDayData.stream().collect(Collectors.toMap(StatisticsInfo::getDataType,StatisticsInfo::getStatisticsCount,(item1,item2)->item2));

        /*实时数据*/
        Map<String,Integer> totalCountInfo = statisticsInfoService.getStatisticsInfoActualTime(null);

        Map<String,Object> result = new HashMap<>();
        result.put("preDayData",preDayDataMap);
        result.put("totalCountInfo",totalCountInfo);

        return getSuccessResponseVO(result);
    }

    @RequestMapping("/getWeekStatisticsInfo")
    public ResponseVO getWeekStatisticsInfo(Integer dataType) {


        List<String> dateList = DateUtil.getBeforeDaysDate(7);

        StatisticsInfoQuery param = new StatisticsInfoQuery();
        param.setDataType(dataType);
        param.setStatisticsDateStart(dateList.get(0));
        param.setStatisticsDateEnd(dateList.get(dateList.size()-1));
        param.setOrderBy("statistics_date asc");

        List<StatisticsInfo> statisticsInfoList = null;
        if(!StatisticsTypeEnum.FANS.getType().equals(dataType)){
            statisticsInfoList = statisticsInfoService.findListTotalInfoByParam(param);
        }else {
            statisticsInfoList = statisticsInfoService.findUserCountTotalInfoByParam(param);
        }
        Map<String,StatisticsInfo> dataMap = statisticsInfoList.stream().collect(Collectors.toMap(item->item.getStatisticsDate(), Function.identity(),(date1,date2)->date2));
        List<StatisticsInfo> resultDataList = new ArrayList<>();

        for (String date:dateList){
            StatisticsInfo dataItem = dataMap.get(date);
            if(dataItem==null){
                dataItem = new StatisticsInfo();
                dataItem.setStatisticsCount(0);
                dataItem.setStatisticsDate(date);
            }
            resultDataList.add(dataItem);
        }
        return getSuccessResponseVO(resultDataList);
    }
}
