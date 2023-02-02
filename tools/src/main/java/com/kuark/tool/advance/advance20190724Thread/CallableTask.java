package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author rock
 * @detail
 * @date 2019/9/17 11:22
 */
@Slf4j
public class CallableTask implements Callable<String> {
    private int index;
    public CallableTask(int index){
        this.index=index;
    }
    @Override
    public String call() throws Exception {
        log.info("in call ...");
        Thread.sleep(index*1000);
        log.info("out call ...");
        return "OK";
    }
}
