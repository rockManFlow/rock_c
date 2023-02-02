package com.kuark.tool.base.designMode.observe;

/**
 * 所谓观察者模式：被观察者状态发生改变通知观察者的相应方法
 * Created by caoqingyuan on 2017/6/5.
 */
public class Test {
    public static void main(String[] s){
        Observable observable=new Observable();
        observable.add(new ObserverA());
        observable.add(new ObserverB());
        observable.changed();
    }
}
