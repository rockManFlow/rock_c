package com.kuark.tool.advance.advance20190815.google.event;

import com.google.common.eventbus.AsyncEventBus;

import java.util.concurrent.Executors;

/**
 * @author rock
 * @detail
 * @date 2019/9/3 10:54
 */
public class EventBusFactory {
    private static AsyncEventBus eventBus;

    public static AsyncEventBus loadAsyncEventBus(){
        if(eventBus==null){
            synchronized (EventBusFactory.class){
                if(eventBus==null){
                    eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
                }
            }
        }
        return eventBus;
    }
}
