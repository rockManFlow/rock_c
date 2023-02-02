package com.kuark.tool.advance.advance20190724Thread;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail callback 方式的实现
 * @date 2021/7/13 11:19
 */
public class CallbackMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        RockCallBack callBack=new RockCallBack();
        new Thread(()->{
            synchronized (callBack){
                String startStr = callBack.get();
                System.out.println("startStr:" + startStr);
                if(StringUtils.isEmpty(startStr)){
                    try {
                        System.out.println("release callback lock");
                        //todo wait会释放锁对象，其他线程可以获取锁对象
                        callBack.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String endStr = callBack.get();
                System.out.println("endStr:" + endStr);
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(new CallbackMain.CallbackTask("m111",callBack)).start();
        System.out.println("main end");
    }

    static class CallbackTask implements Runnable{
        private AbstractRockCallBack callBack;
        private String msg;
        public CallbackTask(String msg,AbstractRockCallBack callBack){
            this.msg=msg;
            this.callBack=callBack;
        }

        @Override
        public void run() {
            System.out.println("start run task....");
            synchronized (callBack){
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("end run task");
                    callBack.set(msg+"OK");
                    callBack.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
