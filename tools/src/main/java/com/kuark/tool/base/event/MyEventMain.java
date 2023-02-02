package com.kuark.tool.base.event;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/1/10 14:25
 */
public class MyEventMain {
    public static void main(String[] args){
        MyEventLister lister=new MyEventLister();
        MyEventSource source=new MyEventSource();
        source.addEventLister(lister);
        source.setName("SET");
    }
}
