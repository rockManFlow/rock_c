package com.kuark.tool.model.proxy.statics;

/**
 * 静态代理类，实现了代理接口
 * Created by caoqingyuan on 2017/7/5.
 */
public class ProxyClass implements ParentInter{

    private ParentInter parentInter=new RealImpl();
    @Override
    public void eat(String name) {
        //把具体功能交给具体功能实现类来处理
        parentInter.eat(name);
        System.out.println("静态代理类");
    }
}
