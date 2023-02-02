package com.kuark.tool.base.task;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caoqingyuan on 2016/9/20.
 */
public class TestTask extends TimerTask {
    private static Logger logger=Logger.getLogger(TestTask.class);
    public static void schdule(){
        Timer timer=new Timer();
        timer.schedule(new TestTask(),0L,2*1000);
    }
    @Override
    public void run() {
        logger.info("run start");
        try {
            Thread.sleep(10*1000);
            logger.info("run end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
