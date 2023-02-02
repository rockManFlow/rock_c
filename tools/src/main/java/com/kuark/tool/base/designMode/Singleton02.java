package com.kuark.tool.base.designMode;

/**
 * @author rock
 * @detail 单例 线程安全
 * @date 2020/9/7 14:24
 */
public enum Singleton02 {
    instance;
    public void test(){
        System.out.println("hi");
    }

    public static void main(String[] args) {
        Singleton02 sing1=Singleton02.instance;
        sing1.test();
        Singleton02 sing2=Singleton02.instance;
        System.out.println(sing1.hashCode());
        System.out.println(sing2.hashCode());
    }
}
