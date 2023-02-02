package com.kuark.tool.base.task;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TODO TimerTask必须与Timer结合使用，来执行定时任务或者一次任务
 * Created by caoqingyuan on 2016/8/12.
 */
public class MyTimerTask extends TimerTask{
    private static final Logger logger=Logger.getLogger(MyTimerTask.class);
    public static void schdule(){
        Timer timer=new Timer();
        //索要安排的任务就会执行run方法
        timer.schedule(new MyTimerTask(),0,1000);
    }
    @Override
    public void run() {
        logger.info("run MyTimerTask");
        MyTimerWorker.schdule();
    }
}
