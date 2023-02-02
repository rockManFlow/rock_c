package com.kuark.tool.advance.advance20201111;


import org.apache.commons.lang.StringUtils;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**
 * @author rock
 * @date 2021/6/15 16:25
 */
public class TestMain {
    public static void main(String[] args) {
//        String st="the is dispute log code OPA888989";
//        int index = st.indexOf("log code ")+"log code ".length();
//        System.out.println(st.substring(index));

//        String orderNo="220909051001649145";
//
//        String yyMMdd = new StringBuffer("20").append(new StringBuffer(orderNo.substring(0, 6))).toString();
//        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(yyMMdd.substring(0, 4)), Integer.parseInt(yyMMdd.substring(4, 6)), Integer.parseInt(yyMMdd.substring(6, 8)), 0, 0, 0);
//        System.out.println(localDateTime);
//        System.out.println(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());

//        System.out.println(localDateCompare(stringToLocalDate("20220210","yyyyMMdd"),stringToLocalDate("20220110","yyyyMMdd")));
//
//        LocalDate start = LocalDate.of(2022, 8, 10);
//        LocalDate end = LocalDate.of(2022, 8, 10);
//        System.out.println(end.isBefore(start));

        Double a=3.5d;
        long l = Double.doubleToLongBits(a);
        String bits = Long.toBinaryString(l);
        System.out.println("l:"+l);
        System.out.println("bits:"+bits);


        BigDecimal bigDecimal=new BigDecimal("21.02");
        System.out.println("scale:"+bigDecimal.scale());//2
        System.out.println("value:"+bigDecimal.unscaledValue());//2102

        byte b='1';
        System.out.println((int)b);

//        BigInteger
    }

    public static long localDateCompare(LocalDate startDate, LocalDate endDate){
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式
     */
    public static LocalDate stringToLocalDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(strDate, fmt);
    }
}
