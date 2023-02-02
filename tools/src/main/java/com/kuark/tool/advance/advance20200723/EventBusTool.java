package com.kuark.tool.advance.advance20200723;

import com.google.common.eventbus.EventBus;

/**
 * @author rock
 * @detail todo EventBus实现消息总线模式
 * @date 2020/7/27 15:00
 */
public class EventBusTool {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        StringCustomerListener stringCustomerListener=new StringCustomerListener();
        eventBus.register(stringCustomerListener);

        MyEventListener myEventListener=new MyEventListener();
        eventBus.register(myEventListener);

        //生产者生产消息
        eventBus.post("hello world");

        //会根据不同的消息类型给到不同的消费者中
        MyEvent myEvent=new MyEvent();
        myEvent.setAge(20);
        myEvent.setName("xiaohong");
        eventBus.post(myEvent);
    }
}
