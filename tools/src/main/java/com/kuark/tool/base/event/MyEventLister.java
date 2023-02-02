package com.kuark.tool.base.event;

import java.util.EventListener;

/**
 * @author caoqingyuan
 * @detail 事件监听器
 * @date 2019/1/10 11:53
 */
public class MyEventLister implements EventListener {

    /**
     * 事件发生时的回调方法
     * @param event
     */
    public void callbackMethod(MyEventObject event){
        System.out.println("event:"+event);
        System.out.println("事件发生时回调方法执行");
    }
}
