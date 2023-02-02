package com.kuark.tool.base.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desc:
 * Author: songshibin
 * Create Date: 2016/11/5
 */
public class DateUtil {

    public DateUtil() {

    }

    /**
     * 得到当前的日期
     *
     * @return string
     */
    public static String getCurrentDate(String formatStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(new Date());
    }
    public static Date calculateFirstRunTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        if (calendar.getTime().after(new Date())) {
            return calendar.getTime();
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            return calendar.getTime();
        }
    }
    /**
     * 两个时间相差多少天
     * @param dateA
     * @param dateB
     * @return
     * @throws ParseException
     */
    public static long dateAReduceDateBd(Date dateA,Date dateB) throws ParseException {
        long s=0;
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String modelA = df.format(dateA);
        String modelB = df.format(dateB);
        dateA = df.parse(modelA);
        dateB = df.parse(modelB);
        long l = dateA.getTime() - dateB.getTime();
        s = l/(24*60*60*1000);
        return s;
    }




}
