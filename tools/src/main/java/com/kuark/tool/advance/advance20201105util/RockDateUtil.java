package com.kuark.tool.advance.advance20201105util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * 时区转换
 */
public class RockDateUtil {
    /**
     * 把指定时区的字符串转成UTC时间--也可以转成其他时区的时间  ---验证OK
     * @param time
     * @return
     */
    public static LocalDateTime parseStringToUtcTime(String time, ZoneId zone) {
        TimeZone currentZone = TimeZone.getDefault();
        System.out.println("currentZone:"+currentZone.getID());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.parse(time, df);
        System.out.println(localDateTime);
        return localDateTime.atZone(zone).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    public static void main(String[] args) {
        //指定时区的时间串，转成指定另外一个时区的时间如何操作
        String d="2022-08-13 16:20:20";
//        ZoneOffset zoneOffset = ZoneOffset.of("+1");
        TimeZone tz = TimeZone.getTimeZone("GMT+1");
        System.out.println(parseStringToUtcTime(d,tz.toZoneId()));
    }
}
