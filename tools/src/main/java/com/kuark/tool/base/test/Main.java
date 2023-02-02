package com.kuark.tool.base.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by caoqingyuan on 2016/9/21.
 */
public class Main {

    public static void main(String[] args){
        //以指定的delim进行拆分字符串
        //如果不传，默认分隔符是“空格”、“制表符(‘\t’)”、“换行符(‘\n’)”、“回车符(‘\r’)”
//        StringTokenizer stringTokenizer=new StringTokenizer("this is, a one people, to street"," ");
//        while (stringTokenizer.hasMoreTokens()){
//            System.out.println(stringTokenizer.nextToken());
//        }
//        double n=0;
//        DecimalFormat format=new DecimalFormat("%.2f");
//        System.out.println(format.format(n));
//        System.out.println(String.format("%.2f",n));
//        format();

//        String st="我国";
//        byte[] bytes = st.getBytes();
//        System.out.println(bytes.length);
//
//        String st2="m";
//        byte[] bytes2 = st2.getBytes();
//        System.out.println(bytes2.length);

        System.out.println(Enum.ColorEnum.green!=Enum.ColorEnum.green);
        System.out.println(Enum.ColorEnum.green!=Enum.ColorEnum.yellow);
        System.out.println(Enum.ColorEnum.green==Enum.ColorEnum.green);
    }
}
