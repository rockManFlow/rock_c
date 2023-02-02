package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail ÓÐÐòÊä³ö 1,2,3,4
 * @date 2019/9/16 19:27
 */
@Slf4j
public class ArrayTask implements Runnable{
    private static int index=1;

    private String threadName;
    private int num;
    public ArrayTask(String threadName,int num){
        this.threadName=threadName;
        this.num=num;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (ArrayTask.class) {
                if (num % index == 0) {
                  log.info("num=" + num);
                  index++;
                  break;
                }
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                log.error("",e);
            }
        }
    }
}
