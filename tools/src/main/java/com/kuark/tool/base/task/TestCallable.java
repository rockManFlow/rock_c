package com.kuark.tool.base.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/6/13 19:17
 */
public class TestCallable implements Callable {
    private int num=0;
    //10000*100
    @Override
    public Object call() throws Exception {
        for(int i=0;i<10000;i++){
           synchronized (TestCallable.class){
               
           }
        }
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for(int i=0;i<100;i++) {
            TestCallable tc = new TestCallable();
            FutureTask task = new FutureTask(tc);
            new Thread(task).start();
            task.get();
        }
    }


}
