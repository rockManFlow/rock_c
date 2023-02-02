package com.kuark.tool.base.parallel.master_worker;

import java.util.Map;
import java.util.Queue;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/4/24 13:39
 */
public class Worker implements Runnable {
    protected Queue<Object> workerQueue;
    protected Map<String,Object> resultMap;

    public void setWorkerQueue(Queue<Object> workerQueue) {
        this.workerQueue = workerQueue;
    }
    public void setResultMap(Map<String,Object> resultMap){
        this.resultMap=resultMap;
    }

    //具体任务处理
    private Long handle(Integer input){
        Long sum=1L;
        for(int i=0;i<3;i++){
            sum=sum*input;
        }
        return sum;
    }
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Object work = workerQueue.poll();
            if (work == null) {
                return;
            }
            Long handle = handle((Integer) work);
            resultMap.put(Integer.toString(handle.hashCode()), handle);
        }
    }
}
