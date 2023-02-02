package com.kuark.tool.base.task;

import com.kuark.tool.base.thread.CallThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/24 16:56
 */
public class StressTask {

    public static void main(String[] args){
        int max=10;
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<max;i++){
            pool.submit(new CallThread("REQ"+i));
        }
        System.out.println("main end");
    }
}
