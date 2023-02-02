package com.kuark.tool.base.enums;

/**
 * 枚举类就是一个常量的集合类
 * Created by caoqingyuan on 2017/1/13.
 */
public class EnumMain {
    public static void main(String[] args){
        System.out.println("start");
//        System.out.println(CheckCode.RED);
//        System.out.println(CheckCode.RED.getCode());
//        System.out.println(CheckCode.RED.getValue());
        EnumCheck.page.gets();
        EnumCheck.age.gets();
        EnumCheck.setName("ssss");
        System.out.println(EnumCheck.getName());
        System.out.println(EnumCheck.age);
        System.out.println("end");
    }
}
