package com.example.administrator.shadowapplication.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/2/002
 */

public class DateUtil {
    /**
     * 获取该月天数
     */
    public static int getCurrentMonthDay(long millSec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSec);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int dateCount = calendar.get(Calendar.DATE);
        return dateCount;
    }

    /**
     * 获取当月第一天
     */
    public static long getFirOfMonth(long millSec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSec);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * 获取上一月/下一月
     */
    public static long getLastOrNextMonth(long millSec, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millSec);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTimeInMillis();
    }

    /**
     * 格式化到月份
     */
    public static String long2str(long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(new Date(millSec));
    }
}
