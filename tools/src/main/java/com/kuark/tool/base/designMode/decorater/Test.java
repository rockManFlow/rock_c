package com.kuark.tool.base.designMode.decorater;

/**
 * Created by caoqingyuan on 2017/6/1.
 */
public class Test {
    public static void main(String[] s){
        Compent component =new BeginCompent();//原来的对象
        System.out.println("------------------------------");
        component.method();//原来的方法
        DecoraterA concreteDecoratorA = new DecoraterA(component);//装饰成A
        System.out.println("------------------------------");
        concreteDecoratorA.method();//原来的方法
        concreteDecoratorA.methodA();//装饰成A以后新增的方法
    }
}
