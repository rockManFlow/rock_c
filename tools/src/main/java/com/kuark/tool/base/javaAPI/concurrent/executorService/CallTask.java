package com.kuark.tool.base.javaAPI.concurrent.executorService;

import java.util.concurrent.Callable;

/**
 * Thread+Runable这两个实现线程的不可以获取到线程执行的结果，除非共享变量等。
 * Callable类似于Runable，但可以获取线程执行结果.
 * 而这个实现类的执行基本上是结合ExecutorTask类来执行该线程
 * Created by caoqingyuan on 2016/8/12.
 */
public class CallTask implements Callable{

    @Override
    public Object call() throws Exception {
        int a=0;
        int b=9;
        while(true){
            if(a<=b){
                ++a;
                System.out.println("a+b="+(a+b));
            }else{
                System.out.println("sleep 30s");
                Thread.sleep(30*1000);
                a=0;
            }
        }
    }
}
