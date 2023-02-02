package com.kuark.tool.model.thread;

import org.junit.Test;

/**
 * 测试线程池
 * Created by caoqingyuan on 2016/9/9.
 */
public class ConcurrenceTest {
    private static ThreadPoolTask threadPoolTask=new ThreadPoolTask();
    @Test
    public void main(){
        String text="发送消息";
        int i=0;
        while (true){
            for(;i<100;i++){
                BaseExecuteTask task=new BaseExecuteTask();
                task.work(text+i);
                threadPoolTask.submit(task);
                if(i==50){
                    break;
                }
            }
            if(i==50){
                break;
            }
        }
    }
}
