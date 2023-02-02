package com.kuark.tool.base.event;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caoqingyuan
 * @detail 事件源：自己任意需要监听的事件发生地
 * @date 2019/1/10 11:57
 */
public class MyEventSource {
    private Set<MyEventLister> listers=null;
    private String name;
    public MyEventSource(){
        listers=new HashSet<>();
    }

    /**
     * 添加事件监听器
     * @param eventListener
     * @return
     */
    public boolean addEventLister(MyEventLister eventListener){
        return listers.add(eventListener);
    }

    //当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
    protected void notifies(MyEventObject myEventObject){
        MyEventLister cel = null;
        Iterator<MyEventLister> iterator = listers.iterator();
        while(iterator.hasNext()){
            cel = iterator.next();
            cel.callbackMethod(myEventObject);
        }
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
        MyEventObject myEventObject=new MyEventObject(name);
        notifies(myEventObject);
    }
}
