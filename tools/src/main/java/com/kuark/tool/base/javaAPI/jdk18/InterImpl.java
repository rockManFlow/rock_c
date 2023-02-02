package com.kuark.tool.base.javaAPI.jdk18;

/**
 * Created by caoqingyuan on 2017/10/25.
 */
public class InterImpl implements Inter {
    @Override
    public void call() {
        System.out.println("call");
    }

    public static void main(String[] args){
//        InterImpl inter = new InterImpl();
//        inter.read();
//        inter.call();
//        inter.show();
        //lambda  匿名方法，主要是简便的实现接口中唯一抽象方法
        Inter inter1=()->{
            System.out.println("show");
        };
        inter1.call();
    }
}
