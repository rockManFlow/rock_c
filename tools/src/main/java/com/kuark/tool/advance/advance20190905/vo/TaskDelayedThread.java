package com.kuark.tool.advance.advance20190905.vo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author rock
 * @detail
 * @date 2019/9/9 15:10
 */
@Slf4j
public class TaskDelayedThread {

    public TaskDelayedThread(){
    }

    private Executor executor= Executors.newFixedThreadPool(10);

    public static DelayQueue<TaskDelayedVo> delayQueue=new DelayQueue();

    private Thread daemonThread;
    public void init(){
        daemonThread=new Thread(()->execute());
        daemonThread.setName("Daemon Thread");
        daemonThread.start();
    }

    private void execute(){
        System.out.println("start execute");
        while (true){
            try {
                TaskDelayedVo delayed=delayQueue.take();
                //延迟队列获取不为空，则执行
                if(delayed!=null){
                    Runnable taskDelay = delayed.getTaskDelay();
                    if(taskDelay==null){
                        continue;
                    }
                    executor.execute(taskDelay);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("in execute");
        }

    }

    public static void main(String[] args){
        TaskDelayedThread taskDelayedThread=new TaskDelayedThread();
        taskDelayedThread.delayQueue.add(new TaskDelayedVo(50000, new Runnable() {
            @Override
            public void run() {
                log.info("Run Task Main1");
            }
        }));
        taskDelayedThread.delayQueue.add(new TaskDelayedVo(3000, new Runnable() {
            @Override
            public void run() {
                log.info("Run Task Main2");
            }
        }));
        taskDelayedThread.init();
        log.info("Main end");
    }
}
