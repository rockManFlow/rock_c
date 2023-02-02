package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail 线程基本信息实践
 * @date 2019/9/27 14:51
 */
@Slf4j
public class ThreadInfo {
    public static void main(String[] a) throws InterruptedException {
//        interruptStatus();
        joinInfo();
    }

    static public void joinInfo() throws InterruptedException {
    Thread threadA =new Thread(
            new Runnable() {
              private String result;

              @Override
              public void run() {
                log.info("joinInfo threadA start");
                try {
                  Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                  log.error("", e);
                }
                result = "threadA OK";
                System.out.println(result);
              }
            });
        threadA.start();

        //同步作用，这个也是阻塞，当前线程，只有等threadA执行完成之后才会继续执行
        threadA.join();
        System.out.println("end");
    }

    static public void interruptStatus() throws InterruptedException {
    Thread threadB =
        new Thread(){
              int num = 0;
              @Override
              public void run() {
                while (true) {
                  if (isInterrupted()) {
                    System.out.println("当前线程中断");
                    break;
                  }
                  num++;
                  if (num % 100000 == 0) {
                    System.out.println("running");
                  }
                }
              }
            };
        threadB.start();

        Thread.sleep(1*1000);
        System.out.println("main");

        //中断只是设置一个中断状态，只能中断阻塞中的线程，阻塞之后，会把状态设置为false
        threadB.interrupt();
        System.out.println("isInterrupted Status="+threadB.isInterrupted());
    }

    public static void currentThreadInterrupt() throws InterruptedException {
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("run start");
                try{
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    log.error("",e);
                }
            }
        });
        threadA.start();

        Thread.sleep(1*1000);
        //当前线程中断
        threadA.interrupt();
    }

}
