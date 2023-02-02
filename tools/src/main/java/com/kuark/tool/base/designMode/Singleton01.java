package com.kuark.tool.base.designMode;

/**
 * @author rock
 * @detail 线程安全，单例
 * @date 2020/9/7 14:15
 */
public class Singleton01 {
    private static class SingletonHandler{
        private static Singleton01 singleton=new Singleton01();
    }
    private Singleton01(){

    }
    public static Singleton01 getInstance(){
        return SingletonHandler.singleton;
    }
}
