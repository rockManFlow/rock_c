package com.kuark.tool.advance.advance20191114.base;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * @author rock
 * @detail
 * @date 2019/11/14 16:21
 */
public class BaseMain {
    public static void main(String[] args) throws UnsupportedEncodingException {
        char c='c';
        String cStr="汉";
        System.out.println("UTF-8："+new String(cStr.getBytes("UTF-8")));
        BigDecimal b1=new BigDecimal("0.8");
        BigDecimal b2=new BigDecimal(0.8);
        System.out.println("b1="+b1.floatValue()/2);
        System.out.println("b2="+b2.floatValue()/2);
    }
}
