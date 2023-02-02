package com.kuark.tool.base.designMode.decorater;

/**
 * 装饰器模式
 * Created by caoqingyuan on 2017/6/1.
 */
public class DecoraterA extends Decorater {
    public DecoraterA(Compent compent) {
        super(compent);
    }
    public void methodA(){
        System.out.println("被装饰器A扩展的功能");
    }
    public void method(){
        System.out.println("加一层A包装");
        compent.method();
        System.out.println("装饰A功能结束");
    }
}
