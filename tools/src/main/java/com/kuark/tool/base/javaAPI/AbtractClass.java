package com.kuark.tool.base.javaAPI;

import java.io.File;

/**
 * @author rock
 * @detail
 * @date 2019/9/24 15:48
 */
public class AbtractClass {
    private static void test(){
        String s=" One ";
        String b=s;
        s.toUpperCase();
        System.out.println(s);
        s.trim();
        System.out.println(s+b);
    }

    public static void main(String[] args){
        test();

        StringBuffer a=new StringBuffer("A");
        StringBuffer b=new StringBuffer("B");
        op(a,b);
        System.out.println("main="+a+","+b);//AB,B
    }

    public static void op(StringBuffer a,StringBuffer b){
        a.append(b);
        b=a;
        System.out.println("op="+b);//AB
    }
}
