package com.kuark.tool.base.event;

import java.util.EventObject;

/**
 * @author caoqingyuan
 * @detail 事件：封装事件源对象及跟事件相关的信息
 * @date 2019/1/10 11:47
 */
public class MyEventObject extends EventObject {
    private String name;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MyEventObject(Object source) {
        super(source);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }
}
