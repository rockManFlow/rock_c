package com.kuark.tool;

import com.kuark.tool.jdk.Person;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/2/21 15:13
 */
public class Test {

    public static void main(String[] args){
        String mmm_dd_yyyy = timestemp2Str(1582239599L, "MMM dd yyyy");
        System.out.println(mmm_dd_yyyy);

        testDiffDateLocales();
    }

    /**

     * 2种不同的Locale的创建方法

     */

    private static void testDiffDateLocales() {
        // date为2013-09-19 14:22:30
        Date date =new Date(113,8, 19,14, 22,30);
        // 创建“简体中文”的Locale

        Locale localeCN = Locale.SIMPLIFIED_CHINESE;

        // 创建“英文/美国”的Locale

        Locale localeUS =new Locale("en","US");

        // 获取“简体中文”对应的date字符串

        String cn = DateFormat.getDateInstance(DateFormat.MEDIUM, localeCN).format(date);

        // 获取“英文/美国”对应的date字符串

        String us = DateFormat.getDateInstance(DateFormat.MEDIUM, localeUS).format(date);
        System.out.printf("cn=%s\nus=%s\n", cn, us);
    }

    public static String timestemp2Str(Long timestemp,String dateFormat){
        ZoneId.systemDefault().getId();
        //设置当前系统时区OffsetDateTime.now().getOffset()
        LocalDateTime localDateTime =LocalDateTime.ofEpochSecond(timestemp,0, OffsetDateTime.now().getOffset());
        System.out.println(localDateTime);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(dateFormat, Locale.UK);
        return localDateTime.format(formatter);
    }

    /**
     * 是hashMap变的安全
     */
    public static void hashMapSafe(){
        HashMap map=new HashMap();
        Map synchronizedMap = Collections.synchronizedMap(map);
        Collections.synchronizedList(new ArrayList<>());
    }

    public static void stringFormat(){
        String st="hehehe%16d";
        String format = String.format(st, 6);
        System.out.println("format:"+format);
    }


    /**
     * switch使用注意问题
     */
    public static void switchErr(){
        String st=null;
        switch (st){
            case "11":
                System.out.println("11");
                break;
            case "22":
                System.out.println("22");
                break;
            default:
                System.out.println("default");
        }
    }

}
