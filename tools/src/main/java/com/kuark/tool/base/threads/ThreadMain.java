package com.kuark.tool.base.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by caoqingyuan on 2018/1/17.
 */
public class ThreadMain {
    private static final Logger logger= LoggerFactory.getLogger(ThreadMain.class);
    public static void main(String[] args) throws InterruptedException {
        Date startDate=new Date();
        for(int i=0;i<100000;i++){
            PayoffWork work=new PayoffWork();
            MyThreadPool.getThreadPool().execute(work);
        }
        logger.info("work finish");

        while (true) {
            if (PayoffWork.endDate == null) {
                Thread.sleep(2*1000L);
            }else{
                logger.info("sum time:"+(PayoffWork.endDate.getTime()-startDate.getTime())/1000+"s");
                break;
            }
        }
        logger.info("main end");
        ((ThreadPoolExecutor)MyThreadPool.getThreadPool()).shutdown();
        logger.info("终止线程池");
    }
}
