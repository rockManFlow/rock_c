package com.kuark.tool.advance.advance20190724Thread.pool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPoolThreeMain {
    static {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable)-> log.error("Thread {} got exception", thread, throwable));
    }
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            System.out.println("start run");
            throw new RuntimeException("Test AAAAA");
        });
        t.start();

        Thread t2=new Thread(()->{
            System.out.println("start run2");
            throw new RuntimeException("Test BBBBB");
        });
        t2.start();
    }
}
