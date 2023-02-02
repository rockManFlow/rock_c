package com.kuark.tool.advance.advance20190815.google.event;

import com.google.common.eventbus.AsyncEventBus;
import com.kuark.tool.advance.advance20190815.google.BaseEvent;

/**
 * @author rock
 * @detail
 * @date 2019/9/3 11:09
 */
public class EventBusImpl {
    private AsyncEventBus eventBus;
    public EventBusImpl(){
        eventBus=EventBusFactory.loadAsyncEventBus();
    }

    /**
     * 注册
     * @param object
     */
    public void register(Object object){
        eventBus.register(object);
    }

    /**
     * 发送
     * @param event
     */
    public void post(BaseEvent event){
        eventBus.post(event);
    }

    public void unregister(Object object){
        eventBus.unregister(object);
    }
}
