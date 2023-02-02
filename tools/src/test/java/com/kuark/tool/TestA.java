package com.kuark.tool;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/19 21:54
 */
public class TestA {
    public static void show(){
        System.out.println("1111");
    }

    public static void main(String[] args) throws ParseException {
        Long b=0L;
        System.out.println(b.equals(0L));

        Date time = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        System.out.println(time);

        String format = LocalDate.now(TimeZone.getTimeZone("Africa/Lagos").toZoneId()).format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(format);


//        BigDecimal bigDecimal = new BigDecimal(20.445).setScale(2, BigDecimal.ROUND_HALF_UP);
//        System.out.println(bigDecimal);
//        List<BigDecimal> list=new ArrayList<>();
//        list.add(new BigDecimal(1.2));
//        list.add(new BigDecimal(2.1));
//        list.add(new BigDecimal(4));
//        List<BigDecimal> collect = list.stream().sorted((e1, e2) -> {
//            if (e1.compareTo(e2) > 0) {
//                return -1;
//            } else {
//                return 1;
//            }
//        }).collect(Collectors.toList());
//        System.out.println(collect);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String en = "00:01:59";
//        LocalTime localDateTime = LocalTime.parse(en, formatter);
//        int hour = localDateTime.getHour();
//        System.out.println(hour);
//        int minute = localDateTime.getMinute();
//        System.out.println(minute);
//        int second = localDateTime.getSecond();
//        System.out.println(second);
//        LocalDate localDate = LocalDate.now(ZoneId.of("Africa/Lagos"));
//        long toEpochSecond = localDate.atStartOfDay(ZoneId.of("Africa/Lagos")).toEpochSecond();
//        System.out.println(toEpochSecond);
//
//        BigDecimal bigDecimal = new BigDecimal("");
//        System.out.println(bigDecimal);

//        Long citys[]={1000L,2000L};
//        System.out.println(JSONObject.toJSONString(citys));
//        String st=null;
//        List list = JSONObject.parseObject(st, List.class);
//        System.out.println(list);
//
//        Long a=0L;
//        System.out.println(a.equals(0L));//0L.equal 0--false 0L.equal 0L--true
//
//        Long b=1283764217L;
//        Long c=1283764217L;
//        System.out.println(b.equals(c));
//        System.out.println("b<c="+(b<c));
//        System.out.println("b>c="+(b>c));
//
//        String sta="[{\"start\":\"00:00:00\",\"end\":\"23:59:59\"},{\"start\":\"10:00:00\",\"end\":\"13:59:59\"}]";
//        TimePeride[] timePerides = JSONObject.parseObject(sta, TimePeride[].class);
//        System.out.println(timePerides[0].getEnd());
    }
}
