package com.kuark.tool.advance.advance20190815.Bus;

import lombok.Builder;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author rock
 * @detail
 * @date 2019/8/15 17:21
 */
@Data
@Builder
public class Event {
    private String name;
    private String value;

    public void create(){
        //把一个对象的操作变成原子操作
        AtomicReference atomicReference = new AtomicReference("111111");
    }

    public String toString(){
        return "Name:"+name+"|Value:"+value;
    }
}
