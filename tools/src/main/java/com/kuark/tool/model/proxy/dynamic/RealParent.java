package com.kuark.tool.model.proxy.dynamic;

/**
 * 具体业务逻辑类
 * Created by caoqingyuan on 2017/7/5.
 */
public class RealParent implements Parente {
    @Override
    public void eat(String name) {
        System.out.println("要吃："+name);
    }

    @Override
    public void drink(String name) {
        System.out.println("我要喝："+name);
    }
}
