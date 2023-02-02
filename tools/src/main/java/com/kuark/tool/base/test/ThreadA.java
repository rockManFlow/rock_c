package com.kuark.tool.base.test;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/12/28 10:12
 */
public class ThreadA extends Thread {
    @Override
    public void run() {
        System.out.println("start ThreadA run");

        int num=0;
        while (true){
            if(isInterrupted()){
                System.out.println("检测到中断退出ThreadA");
                break;
            }
            num++;
            try{
                sleep(1*60*1000);
            } catch (InterruptedException e) {
                System.out.println("获取到中断异常，当前中断状态isInterrupted()"+isInterrupted());
                e.printStackTrace();
            }

            System.out.println("挑出循环冲段状态isInterrupted()"+isInterrupted());

            if(num%100==0){
                System.out.println("打印num日志");
            }
        }
    }
}
