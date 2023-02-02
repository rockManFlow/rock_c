package com.kuark.tool.advance.advance20200723;

import com.google.common.eventbus.Subscribe;

/**
 * @author rock
 * @detail
 * @date 2020/7/27 15:03
 */
public class StringCustomerListener {

    @Subscribe
    public void customer(String msg){
        System.out.println("customer msg:"+msg);
    }
}
