package com.kuark.tool.base.task;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caoqingyuan on 2016/10/21.
 */
public class MyTimerWorker extends TimerTask {
    private static final Logger logger=Logger.getLogger(MyTimerWorker.class);
    public static void schdule(){
        Timer timer=new Timer();
        timer.schedule(new MyTimerWorker(),0);
    }
    @Override
    public void run() {
        logger.info("run MyTimerWorker end");
    }
}
