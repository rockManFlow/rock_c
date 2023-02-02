package com.kuark.tool.base.parallel.master_worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/4/24 13:39
 */
public class Master{
    //任务队列
    protected Queue<Object> workerQueue=new ConcurrentLinkedQueue<>();
    //进程队列
    protected Map<String,Thread> threadMap=new HashMap<>();
    //子任务处理结果集
    protected Map<String,Object> resultMap=new ConcurrentHashMap<>();

    //所有子任务是否处理完成
    public Boolean isComplete(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState()!=Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //提交子任务
    public void submit(Object object){
        workerQueue.add(object);
    }

    //创建几个worker子进程
    public Master(Worker worker,Integer countWorker){
        worker.setWorkerQueue(workerQueue);
        worker.setResultMap(resultMap);
        for(int i=0;i<countWorker;i++){
            threadMap.put(Integer.toString(i),new Thread(worker,Integer.toString(i)));
        }
    }

    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    //执行所有子进程
    public void execute(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            entry.getValue().start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Master master = new Master(new Worker(), 3);
        master.execute();
        for(int i=1;i<=20;i++){
            master.submit(i);
        }
        while (true) {
            Boolean complete = master.isComplete();
            if(complete){
                Map<String, Object> resultMap = master.getResultMap();
                Set<String> setStr = resultMap.keySet();
                Long sum=0L;
                for(String key:setStr){
                    Long o = (Long)resultMap.get(key);
                    sum+=o;
                }
                System.out.println("sum:"+sum);
                return;
            }
            Thread.sleep(1*1000L);
        }

    }
}
