package com.kuark.tool.base.enums;

/**
 * Created by caoqingyuan on 2017/5/18.
 */
public enum  EnumCheck {
    page,age;//在枚举中这种当做的：这个枚举类型的实例化对象，所以可以获得普通的非静态的方法
    private static String name;
    private static int num;

    public void gets(){
        System.out.println("ddddd");
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        EnumCheck.name = name;
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        EnumCheck.num = num;
    }
}
