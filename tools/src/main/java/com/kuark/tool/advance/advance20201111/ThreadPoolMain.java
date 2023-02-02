package com.kuark.tool.advance.advance20201111;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail
 * @date 2021/8/7 15:41
 */
@Slf4j
public class ThreadPoolMain {
    public static void main(String[] args) {
//        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
//        poolExecutor.execute();
        Thread t=new Thread(()->{
            log.info("run");
        });
        t.setName("xxx");
        t.start();
    }
}
