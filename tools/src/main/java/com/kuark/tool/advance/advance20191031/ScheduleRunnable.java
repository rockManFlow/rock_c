package com.kuark.tool.advance.advance20191031;

import lombok.extern.log4j.Log4j;

/**
 * @author rock
 * @detail
 * @date 2019/11/11 16:33
 */
@Log4j
public class ScheduleRunnable implements Runnable {
    private String name;
    public ScheduleRunnable(String name){
        this.name=name;
    }
    @Override
    public void run() {
        log.info("Schedule Run Task Name="+this.name);
    }
}
