package com.kuark.tool.advance.advance20190708;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/8 16:41
 */

public class RunTimer {
    public void schedule(){
        //定时任务执行
        Timer timer=new Timer();
        timer.schedule(new RunTask(),10,10*60*1000);
    }
}
