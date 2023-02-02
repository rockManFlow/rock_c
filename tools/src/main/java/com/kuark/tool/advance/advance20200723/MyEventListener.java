package com.kuark.tool.advance.advance20200723;

import com.google.common.eventbus.Subscribe;

/**
 * @author rock
 * @detail
 * @date 2020/7/27 15:11
 */
public class MyEventListener {

    @Subscribe
    public void consumer(MyEvent event){
        System.out.println("consumer event:"+event);
    }
}
