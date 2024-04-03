package cn.topcheer.pms2.common.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 *
 * @author szs
 * @date 2021-11-20
 */
public class DateUtil {


    /**
     * 判断对象是否为null或者为空字符串，当对象不为Null或者不是空字符串时返回true
     *
     * @return boolean
     * @author szs
     * @date 2021-12-27
     */
    public static boolean isNoEmpty(Object... value) {
        if (value == null) {
            return false;
        }

        for (Object val : value) {
            if (val == null || "".equals(val)) {
                return false;
            }
        }

        return true;
    }


    /**
     * LocalDateTime转字符串
     *
     * @param time    LocalDateTime
     * @param pattern 格式
     * @return 字符串
     * @author szs
     * @date 2021-11-20
     */
    public static String timeToString(LocalDateTime time, String pattern) {
        if (!isNoEmpty(time) || !isNoEmpty(pattern)) {
            return "";
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(time);
    }


    /**
     * LocalDateTime转字符串
     *
     * @param time LocalDateTime
     * @return 字符串
     * @author szs
     * @date 2021-11-20
     */
    public static String timeToString(LocalDateTime time) {

        return timeToString(time, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * LocalDate转字符串
     *
     * @param date    LocalDate
     * @param pattern 格式
     * @return 字符串
     * @author szs
     * @date 2021-11-20
     */
    public static String dateToString(LocalDate date, String pattern) {
        if (!isNoEmpty(date) || !isNoEmpty(pattern)) {
            return "";
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(date);
    }


    /**
     * LocalDate转字符串
     *
     * @param date LocalDate
     * @return 字符串
     * @author szs
     * @date 2021-11-20
     */
    public static String dateToString(LocalDate date) {

        return dateToString(date, "yyyy-MM-dd");
    }


    /**
     * 字符串转 LocalDateTime
     *
     * @param time    时间字符串
     * @param pattern 格式
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime stringToTime(String time, String pattern) {
        if (!isNoEmpty(time) || !isNoEmpty(pattern)) {
            return null;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, df);
    }


    /**
     * 字符串转 LocalDateTime
     *
     * @param time 时间字符串
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime stringToTime(String time) {

        return stringToTime(time, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 字符串转 LocalDate
     *
     * @param date    日期字符串
     * @param pattern 格式
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDate stringToDate(String date, String pattern) {
        if (!isNoEmpty(date) || !isNoEmpty(pattern)) {
            return null;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, df);
    }


    /**
     * 字符串转 LocalDate
     *
     * @param date 日期字符串
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDate stringToDate(String date) {

        return stringToDate(date, "yyyy-MM-dd");
    }


    /**
     * 获取秒时间差
     *
     * @param now LocalDateTime
     * @param end LocalDateTime
     * @return Integer
     * @author yjk
     * @date 2020/12/15 15:06
     */
    public static Integer differSecond(LocalDateTime now, LocalDateTime end) {

        // 获取秒数
        long nowSecond = now.toEpochSecond(ZoneOffset.ofHours(0));
        long endSecond = end.toEpochSecond(ZoneOffset.ofHours(0));
        long l = nowSecond - endSecond;

        return Math.abs(Integer.parseInt(String.valueOf(l)));
    }


    /**
     * 秒转时分秒
     *
     * @param time 秒
     * @return 时分秒
     * @author szs
     * @date 2021-11-16
     */
    public static String secToTime(int time) {
        String timeStr = "";
        int hour = 0;
        int minute;
        int second;
        if (time <= 0) {
            return "00秒";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
            } else {
                hour = minute / 60;
                if (hour > 99) {
                    return "99时59分59秒";
                }
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
            }
        }

        if (hour > 0) {
            timeStr += unitFormat(hour) + "时";
        }

        if (minute > 0) {
            timeStr += unitFormat(minute) + "分";
        }

        timeStr += unitFormat(second) + "秒";

        return timeStr;
    }


    /**
     * 单位格式化
     *
     * @param i int
     * @return String
     * @author szs
     * @date 2021-11-16
     */
    private static String unitFormat(int i) {
        String retStr;
        if (i >= 0 && i < 10) {
            retStr = "0" + i;
        } else {
            retStr = "" + i;
        }

        return retStr;
    }


    /**
     * 获取今天开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getTodayStartTime() {

        return stringToTime(dateToString(LocalDate.now()) + " 00:00:00");
    }


    /**
     * 获取今天结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getTodayEndTime() {

        return getTodayStartTime().plusDays(1).minusSeconds(1);
    }


    /**
     * 获取本周开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getWeekStartTime() {
        // 今日
        LocalDate date = LocalDate.now();

        // 星期几
        int week = date.getDayOfWeek().getValue();

        return stringToTime(dateToString(date.minusDays(week - 1)) + " 00:00:00");
    }


    /**
     * 获取本周结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getWeekEndTime() {

        return getWeekStartTime().plusWeeks(1).minusSeconds(1);
    }


    /**
     * 获取本旬开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getTenDayStartTime() {
        // 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
        int tenDay = LocalDate.now().getDayOfMonth();
        if (tenDay < 11) {
            // 上旬开始时间 = 本月开始时间
            return getMonthStartTime();
        } else if (tenDay < 21) {
            // 中旬开始时间 = 本月开始时间 + 10天
            return getMonthStartTime().plusDays(10);
        } else {
            // 下旬开始时间 = 本月开始时间 + 20天
            return getMonthStartTime().plusDays(20);
        }
    }


    /**
     * 获取本旬结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getTenDayEndTime() {
        // 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
        int tenDay = LocalDate.now().getDayOfMonth();
        if (tenDay < 11) {
            // 上旬结束时间 = 本月开始时间 + 10天 - 1秒
            return getMonthStartTime().plusDays(10).minusSeconds(1);
        } else if (tenDay < 21) {
            // 中旬结束时间 = 本月开始时间 + 20天 - 1秒
            return getMonthStartTime().plusDays(20).minusSeconds(1);
        } else {
            // 下旬结束时间 = 本月结束
            return getMonthEndTime();
        }
    }


    /**
     * 获取上旬开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getLastTenDayStartTime() {
        // 三旬: 上旬1-10日 中旬11-20日 下旬21-31日
        int tenDay = LocalDate.now().getDayOfMonth();
        if (tenDay < 11) {
            // 当前本旬的上旬，取上旬的下旬 开始时间 = 上月开始时间 + 20天
            return getMonthStartTime().minusMonths(1).plusDays(20);
        } else if (tenDay < 21) {
            // 当前本旬的中旬，取本旬的上旬 开始时间 = 本月开始时间
            return getMonthStartTime();
        } else {
            // 当前本旬的下旬，取本旬的中旬 开始时间 = 本月开始时间 + 10天
            return getMonthStartTime().plusDays(10);
        }
    }


    /**
     * 获取上旬结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getLastTenDayEndTime() {
        // 取本旬开始时间 - 1秒
        return getTenDayStartTime().minusSeconds(1);
    }


    /**
     * 获取本月开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getMonthStartTime() {
        // 今日
        LocalDate date = LocalDate.now();

        // 本月第几日
        int day = date.getDayOfMonth();

        return stringToTime(dateToString(date.minusDays(day - 1)) + " 00:00:00");
    }


    /**
     * 获取本月结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getMonthEndTime() {

        return getMonthStartTime().plusMonths(1).minusSeconds(1);
    }


    /**
     * 获取本年开始时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getYearStartTime() {
        // 今日
        LocalDate date = LocalDate.now();

        // 本年第几日
        int day = date.getDayOfYear();

        return stringToTime(dateToString(date.minusDays(day - 1)) + " 00:00:00");
    }


    /**
     * 获取本年结束时间
     *
     * @return LocalDateTime
     * @author szs
     * @date 2021-11-20
     */
    public static LocalDateTime getYearEndTime() {

        return getYearStartTime().plusYears(1).minusSeconds(1);
    }


}