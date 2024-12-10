package com.easylive.utils;


import com.easylive.entity.enums.DateTimePatternEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

    private static final Object lockObj = new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            return getSdf(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    //只返回单个日期。例如，假设今天是2024-12-10，如果传入 3，只会返回"2024-12-07"
    public static String getBeforeDayDate(Integer day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-day);
        return format(calendar.getTime(), DateTimePatternEnum.YYYY_MM_DD.getPattern());
    }

   // 会生成一个日期序列。例如，假设今天是2024-12-10，如果传入 3，会返回["2024-12-07", "2024-12-08", "2024-12-09"]
    public static List<String> getBeforeDaysDate(Integer beforeDays) {
        LocalDate endDate = LocalDate.now();
        List<String> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(int i = beforeDays; i>0; i--){
            dateList.add(endDate.minusDays(i).format(formatter));
        }
        return dateList;
    }
}
