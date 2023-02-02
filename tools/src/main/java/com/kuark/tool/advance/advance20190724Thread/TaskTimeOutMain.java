package com.kuark.tool.advance.advance20190724Thread;

import java.util.concurrent.*;

/**
 * @author rock
 * @detail 任务执行超时设置
 * @date 2022/4/26 10:26
 */
public class TaskTimeOutMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask=new FutureTask(new TimeOutTask());
        new Thread(futureTask).start();
        String result=null;
        try {
            result=futureTask.get(20, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        TimeUnit.SECONDS.sleep(60);
    }

    public static class TimeOutTask implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("start run");
            //模拟remote 调用耗时
            TimeUnit.SECONDS.sleep(60);
            System.out.println("start end");
            return "OK";
        }
    }
}
