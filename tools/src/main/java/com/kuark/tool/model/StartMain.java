package com.kuark.tool.model;


import com.alibaba.fastjson.JSONObject;
import com.kuark.tool.model.activeMQ.QueueTask;
import lombok.Data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by caoqingyuan on 2016/9/13.
 */
public class StartMain {
//    public static void main(String[] args){
//        //TimeTask可以直接起来
//        QueueTask.schedule();
//    }

    public static void main(String[] args) {
        String st = "20200207423215769901334528";
        System.out.println(st.length());

//        LocalDateTime end = LocalDateTime.now(ZoneId.of("Africa/Lagos"));
//        LocalDateTime start = end.withHour(0).withMinute(0).withSecond(0).withNano(0);
//        start = start.minusDays(start.getDayOfWeek().getValue() - 1);
////        start = start.minusMonths(1);
//        String startTime = start.atZone(ZoneId.of("Africa/Lagos")).withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        String endTime = end.atZone(ZoneId.of("Africa/Lagos")).withZoneSameInstant(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//        System.out.println("startTime="+startTime);
//        System.out.println("endTime="+endTime);

        LocalDate localDate=LocalDate.now();
        System.out.println("date="+localDate);
        LocalDate week = localDate.minusDays(localDate.getDayOfWeek().getValue()-1);
        System.out.println("week="+week);
        LocalDate months = localDate.minusMonths(1);
        System.out.println("相差一个月months="+months);

        LocalDate months2 = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当前月第一天日期months2="+months2);

        LocalDate months3 = localDate.with(DayOfWeek.MONDAY);
        System.out.println("本周第一天months3="+months3);

        LocalDateTime localDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        System.out.println("本周第一天months4="+localDateTime.with(DayOfWeek.MONDAY));
    }

}
