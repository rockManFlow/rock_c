package com.kuark.tool.advance.advance20201105util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * @author caoqingyuan
 * @detail
 */
public class DateUtils {
    private static final String DATE_FORMAT="yyyyMMddHHmmss";
    private static final String DATE_FORMAT_BASE="yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_YYYYMMDD="yyyyMMdd";
    public static Boolean isVilad(Date input){
        LocalDate now = LocalDate.now();
        return now.isBefore(Data2LocalDate(input));
    }

    public static Date str2Date(String strDate) throws ParseException{
            return new SimpleDateFormat(DATE_FORMAT_BASE).parse(strDate);
    }

    public static String date2Str(Date date){
        return new SimpleDateFormat(DATE_FORMAT_BASE).format(date);
    }

    public static Boolean isBefore(LocalDate first,LocalDate second){
        return first.isBefore(second);
    }

    /**
     * Date转LocalDate
     * @param input
     * @return
     */
    public static LocalDate Data2LocalDate(Date input){
        Instant instant = input.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * Date转LocalDate
     * @param input
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date input){
        Instant instant = input.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * LocalDateTime转Date
     * @param input
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime input){
        return Date.from(input.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 今天
     */
    public static LocalDateTime nowDateTimeDay(){
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    /**
     * 当前减去指定天数
     */
    public static LocalDateTime nowDateSubtractAssignDay(int differDay){
        LocalDateTime now = LocalDateTime.now();
        return now.minusDays(differDay);
    }

    /**
     * 明天
     */
    public static LocalDate nowDateAddOneDay(){
        LocalDate now = LocalDate.now();
        return now.plusDays(1);
    }

    /**
     * 下月第一天
     */
    public static LocalDate firstDayOfNextMonth(){
        LocalDate now = LocalDate.now();
        LocalDate dayOfNextMonth=now.with(TemporalAdjusters.firstDayOfNextMonth());
        return dayOfNextMonth;
    }

    /**
     * 下周的第一天
     */
    public static LocalDate nowWeekAddNextWeekDay(){
        LocalDate date = LocalDate.now().plusWeeks(1);
        return date.with(DayOfWeek.MONDAY);
    }

    public static Date getLocalDateStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 当前时间减多少天
     * @param day 天数
     * @return
     */
    public static Date dateOp(Integer day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(new Date());
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cd.add(Calendar.DATE, -day);//减多少天
        Date date=cd.getTime();
        return date;
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 相差天数 end-start
     * @param start
     * @param end
     * @return
     */
    public static Long differDay(LocalDateTime start,LocalDateTime end){
        return (end.toLocalDate().toEpochDay() - start.toLocalDate().toEpochDay());
    }

    /**
     * 获取本周的第一天的日期
     * @return
     */
    public static LocalDateTime nowWeekFirstDay(){
        LocalDateTime localDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return localDateTime.with(DayOfWeek.MONDAY);
    }

    /**
     * 本月的第一天日期
     * @return
     */
    public static LocalDateTime nowMonthFirstDay(){
        LocalDateTime localDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static String nowDateTime2Str(){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static Long getNowTimeStemp(){
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static String getNowDateString(){
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYYMMDD));
    }

    /**
     * 计算当前周的起止时间串
     * @return
     */
    public static Map<String, String> weekBeginningAndEnding() {
        Map<String, String> map = new HashMap<>();
        //获取当前自然周中每天的日期集合
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);		//这里设置一周开始时间是星期一
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        String beginTime = format.format(c.getTime());      //获取当前自然周的起始时间
        map.put("begin", beginTime);

        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        String endTime = format.format(c.getTime());        //当前自然周的截止时间
        map.put("end", endTime);
        return map;
    }

    public static void main(String[] args){
//        LocalDateTime date = nowDateTimeDay();
//        System.out.println(date);
//        System.out.println("date="+new Date());
        LocalDate localDate=LocalDate.now();
        System.out.println("date="+localDate);
    }
}
