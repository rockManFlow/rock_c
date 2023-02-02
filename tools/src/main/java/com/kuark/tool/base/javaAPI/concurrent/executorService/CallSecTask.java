package com.kuark.tool.base.javaAPI.concurrent.executorService;

import java.util.concurrent.Callable;

/**
 * Created by caoqingyuan on 2016/8/12.
 */
public class CallSecTask implements Callable{
    @Override
    public Boolean call() throws Exception {
        System.out.println("this is second task test");
        return true;
    }
}
