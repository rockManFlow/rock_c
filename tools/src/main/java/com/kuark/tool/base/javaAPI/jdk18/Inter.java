package com.kuark.tool.base.javaAPI.jdk18;

/**
 * Created by caoqingyuan on 2017/10/25.
 */
public interface Inter {
    public void call();
    //jdk1.8新特性：允许你在接口中定义使用default注释的方法，相当于抽象类中实现的普通方法
    default void show(){
        System.out.println("interface show");
    }

    default void read(){
        System.out.println("interface read");
    }
}
