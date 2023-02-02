package com.kuark.tool.model.proxy.statics;

/**
 * 具体功能实现类
 * Created by caoqingyuan on 2017/7/5.
 */
public class RealImpl implements ParentInter{
    @Override
    public void eat(String name) {
        System.out.println("xiaohong 吃"+name);
    }
}
