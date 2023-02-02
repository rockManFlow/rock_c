package com.kuark.tool.model.proxy.statics;

/**
 * 测试静态代理
 * Created by caoqingyuan on 2017/7/5.
 */
public class StaticMain {
    public static void main(String[] s){
        ParentInter parentInter=new ProxyClass();
        parentInter.eat("苹果");
    }
}
