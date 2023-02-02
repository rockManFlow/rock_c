package com.kuark.tool.advance.advance20190815;

import com.kuark.tool.advance.advance20190815.Bus.Event;
import com.kuark.tool.advance.advance20190815.Bus.EventBus;
import com.kuark.tool.advance.advance20190815.Bus.SubscriberImpl;
import com.kuark.tool.advance.advance20190815.Bus.SubscriberImpl2;

/**
 * @author rock
 * @detail
 * @date 2019/8/15 17:07
 */
public class MainJava {
    /**
     * 响应式编程+消息总线+观察者模式
     */

    public static void main(String[] args){
        EventBus bus=new EventBus();
        new SubscriberImpl(bus);
        new SubscriberImpl2(bus);
        //发布消息
        bus.publish(Event.builder().name("呵呵").value("123").build());

        //

    }

}
