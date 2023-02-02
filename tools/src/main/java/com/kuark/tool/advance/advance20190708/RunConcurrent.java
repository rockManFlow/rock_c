package com.kuark.tool.advance.advance20190708;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/8 16:55
 */
public class RunConcurrent {
    public static ExecutorService loadThreadPool(){
        return new ThreadPoolExecutor(5,10,1*60*1000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                throw new RejectedExecutionException();
            }
        });
    }

    public class RunnableTask implements Runnable{

        private Map runMap;
        private String key;
        public RunnableTask(Map runMap,String key){
            this.runMap=runMap;
            this.key=key;
        }
        @Override
        public void run() {
//            ReentrantLock lock=new ReentrantLock();
//            lock.lock();
            synchronized (runMap){
                if(runMap.get(key)!=null){
                    System.out.println("key:"+runMap.get(key));
                    runMap.remove(key);
                    System.out.println("size:"+runMap.size());
                }
            }
        }
    }

    public static void main(String[] args){
        ExecutorService pool = RunConcurrent.loadThreadPool();
        HashMap<String,String> runMap=new HashMap<>();
        for(int i=0;i<100;i++){
            runMap.put(""+i,"value"+i);
        }
        for(int j=0;j<100;j++){
            pool.submit(new RunConcurrent().new RunnableTask(runMap,""+j));
        }
        System.out.println("main end");
    }

}
