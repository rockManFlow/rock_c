package com.kuark.tool.advance.advance20191120;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.*;

/**
 * @author rock
 * @detail
 * @date 2020/2/17 16:00
 */
public class DateParseUtils {
    public static void main(String[] args) throws ParseException {
        dateParse();
    }

    public static void dateParse() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前系统时区与指定时区比较
        format.setTimeZone(TimeZone.getTimeZone("Africa/Lagos"));
        //text-date
        Date parse = format.parse("2020-02-10 10:16:20");
        System.out.println("把当前日期转换成指定时区的时间："+parse);
        String format1 = format.format(new Date());
        System.out.println("指定时区date->text:"+format1);
    }

    public static void dateD(){
        String st="1582041600000";
        String timedate = timeStampdate(st);
        System.out.println(timedate);
    }

    /**
     * todo Java中时间戳转时间都是使用的Date的中有输入毫秒的构造方法
     * 时间戳转时间格式
     * @param timeStamp 精确到秒的字符串
     * @return
     */
    public static String timeStampdate(String timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(Long.valueOf(timeStamp+"000")));//到毫秒
    }
}
