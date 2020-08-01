package com.dupake.common.utils;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 日期工具类
 * @Author dupake
 * @Date 2020/6/10 14:28
 */
public class DateUtil {
    /**
     * Date 转为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param time
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    public static LocalDateTime getLocalDateTimeOfMillisecond(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static LocalDateTime getLocalDateTimeOfSecond(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * 获取指定时间的指定格式
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 将字符串按照指定格式转换为时间
     *
     * @param time    要转换的时间字符串
     * @param pattern 格式
     * @return
     */
    public static LocalDateTime strToTime(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }


    /**
     * 时间减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }


    /**
     * 获取所在周的开始时间，周一的：00:00:00
     *
     * @param time
     * @return
     */
    public static LocalDateTime getWeekStart(LocalDateTime time) {
        LocalDateTime monday = time.with(DayOfWeek.MONDAY);
        return getDayStart(monday);
    }

    /**
     * 获取所在周的结束时间 周日的23:59:59
     *
     * @param time
     * @return
     */
    public static LocalDateTime getWeekEnd(LocalDateTime time) {
        LocalDateTime monday = time.with(DayOfWeek.SUNDAY);
        return getDayEnd(monday);
    }

    /**
     * 获取时间所在月的开始时间
     *
     * @param time
     * @return
     */
    public static LocalDateTime getMonthStart(LocalDateTime time) {
        LocalDateTime firstDay = time.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(firstDay);
    }

    /**
     * 获取时间所在月的结束时间
     *
     * @param time
     * @return
     */
    public static LocalDateTime getMonthEnd(LocalDateTime time) {
        LocalDateTime lastDay = time.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(lastDay);
    }

    /**
     * 获取某月的开始时间
     *
     * @param offset 0本月，1下个月，-1上个月，依次类推
     * @return
     */
    public static LocalDateTime getMonthStartByOffset(int offset) {
        LocalDateTime lastDay = LocalDateTime.now().plusMonths(offset).with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(lastDay);
    }

    /**
     * 获取某月的结束时间
     *
     * @param offset 0本月，1下个月，-1上个月，依次类推
     * @return
     */
    public static LocalDateTime getMonthEndByOffset(int offset) {
        LocalDateTime lastDay = LocalDateTime.now().plusMonths(offset).with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(lastDay);
    }

    /**
     * 获取某年的开始时间
     *
     * @param offset offset 0今年，1明年，-1去年，依次类推
     * @return
     */
    public static LocalDateTime yearStartByOffset(int offset) {
        LocalDateTime firstDay = LocalDateTime.now().plusYears(offset).with(TemporalAdjusters.firstDayOfYear());
        return getDayStart(firstDay);
    }


    /**
     * 获取某年的结束时间
     *
     * @param offset offset 0今年，1明年，-1去年，依次类推
     * @return
     */
    public static LocalDateTime yearEndByOffset(int offset) {
        LocalDateTime end = LocalDateTime.now().plusYears(offset).with(TemporalAdjusters.lastDayOfYear());
        return getDayEnd(end);
    }


    /**
     * 获取某周的开始日期时间
     *
     * @param offset 0本周，1下周，-1上周，依次类推
     * @return
     */
    public static LocalDateTime weekStartByOffset(int offset) {
        LocalDateTime localDate = LocalDateTime.now().plusWeeks(offset);
        LocalDateTime start = localDate.with(DayOfWeek.MONDAY);
        return getDayStart(start);
    }

    /**
     * 获取某周的结束日期时间
     *
     * @param offset 0本周，1下周，-1上周，依次类推
     * @return
     */
    public static LocalDateTime weekEndByOffset(int offset) {
        LocalDateTime localDate = LocalDateTime.now().plusWeeks(offset);
        LocalDateTime end = localDate.with(DayOfWeek.SUNDAY);
        return getDayEnd(end);
    }

    /**
     * 比较两个日期相差天数
     *
     * @return
     */
    public static int DateDiffer(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getDays();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 时间戳转localDateTime
     *
     * @param time
     * @return
     */
    public static LocalDateTime convertLongToLDT(long time) {
        return LocalDateTime.ofEpochSecond(time / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 获取上一天的时间戳
     *
     * @param time
     * @return
     */
    public static long getLastTime(long time) {
        LocalDateTime localDateTime = convertLongToLDT(time).plusDays(-1);
        return getMilliByTime(localDateTime);
    }


    //获取一天的开始时间，2017,7,22 00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }


    //计算时间差

    /**
     * @param minTime 时间 2020-07-29T17:37:44.254451
     * @param maxTime 时间  2020-07-30T17:37:44.254650
     * @return 1天：24 小时：1440 分钟：86400000 毫秒：86400000199000 纳秒】
     */
    public static long subTime(LocalDateTime minTime, LocalDateTime maxTime) {
        System.out.println("计算两个时间的差：" + minTime + "-----------" + maxTime);
        Duration duration = Duration.between(minTime, maxTime);
        long days = duration.toDays(); //相差的天数
        return days;
    }


}
