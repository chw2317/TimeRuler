package com.hdl.timeruler.utils;

import com.hdl.timeruler.bean.TimeSlot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * Created by HDL on 2017/7/25.
 */

public class DateUtils {
    /**
     * 获取当前时间的起点（00:00:00）
     *
     * @param currentTime
     * @return
     */
    public static long getTodayStart(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(currentTime));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当前时间的终点（23:59:59）
     *
     * @param currentTime
     * @return
     */
    public static long getTodayEnd(long currentTime) {
        return getTodayStart(currentTime) + 24 * 60 * 60 * 1000L - 1000;
    }

    /**
     * 获取指定时间的年月日
     *
     * @param currentTime
     * @return
     */
    public static String getDateByCurrentTiem(long currentTime) {
        return new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
    }

    /**
     * 获取指定时间的年月日
     *
     * @param currentTime
     * @return
     */
    public static String getDateTime(long currentTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
    }

    /**
     * 获取指定日期的时间（如：10:11:12）
     *
     * @param currentTime
     * @return
     */
    public static String getTime(long currentTime) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(currentTime));
    }

    /**
     * 根据当前的秒数计算时间
     *
     * @param currentSecond
     * @return
     */
    public static String getTimeByCurrentSecond(int currentSecond) {
        currentSecond = currentSecond / 60;
        int minute = currentSecond % 60;
        int hour = currentSecond / 60;
        if (hour >= 24) {
            hour = hour % 24;
        }
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }

    /**
     * 根据当前的秒数计算时间
     *
     * @param currentSecond
     * @return
     */
    public static String getTimeByCurrentHours(int currentSecond) {
        currentSecond = currentSecond * 10;
        currentSecond = currentSecond / 60;
        int minute = currentSecond % 60;
        int hour = currentSecond / 60;
        if (hour >= 24) {
            hour = hour % 24;
        }
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }

    public static void main(String[] args) {
        TimeSlot timeSlot = new TimeSlot(DateUtils.getTodayStart(System.currentTimeMillis()),DateUtils.getTodayStart(System.currentTimeMillis()) - 60 * 60 * 1000, DateUtils.getTodayStart(System.currentTimeMillis()) + 120 * 60 * 1000);
        System.out.println(timeSlot.toString());
    }
}
