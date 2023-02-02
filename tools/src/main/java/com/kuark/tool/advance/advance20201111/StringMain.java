package com.kuark.tool.advance.advance20201111;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * String长度
 * 要弄清楚 String 的最大长度，首先应该了解 String 类的内部实现。在 String 类中，是使用一个字符数组来维护字符序列的，其声明如下：
 *
 * private final char value[];
 * 这也就是说，String 的最大长度取决于字符数组的最大长度，我们知道，在指定数组长度的时候，可以使用 byte、short、char、int 类型，
 * 而不能够使用 long 类型。这也就是说，数组的最大长度就是 int 类型的最大值，
 * 即 0x7fffffff，十进制就是 2147483647，同理，这也就是 String 所能容纳的最大字符数量。
 */
public class StringMain {
    public static void main1(String[] args) {
//        show();
//        StringBuffer
//        StringBuilder
//        String st="dsdefrnurfreb21337y4bbcsdhbccdcjasn";
//        System.out.println(st.hashCode());
        char va[]=new char['2'];

        Map<String, Object> sourceJson = JSON.parseObject("{}", Map.class);
        System.out.println(sourceJson);

        System.out.println(SeriableVo.class.getCanonicalName());

        //十进制数转成指定进制数

        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.format(new Date());

    }

    public static void main(String[] args) {
        /**
         * string对象与普通的都是一样的。对于改变都是新建内存地址来拷贝一遍
         */
        String a="aaaa";
        String b=new String("bbbb");
        System.out.println("1result out:"+cover(a));
        System.out.println("2result out:"+cover(b));

        System.out.println("1in out:"+a);
        System.out.println("2in out:"+b);
    }

    private static String cover(String input){
        input=input+1;
        return input;
    }

    public static void show(){
        List<String> lista=new ArrayList<>();
        List<String> listb=Collections.EMPTY_LIST;
        List<String> listc=new ArrayList<>();
        listc.add("111");
        lista.addAll(listb);
        lista.addAll(listc);
        System.out.println("OK");
    }
}
