package com.kuark.tool.advance.advance20190724Thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail 卖票
 * @date 2019/9/16 17:38
 */
@Slf4j
public class SaleTicketMain {
    //常量共享对象就不用进行内存可见，因为直接修改的是同一个内存地址
    public static int sum=10;
    public static void main(String[] args){
        log.info("start main");
        //
//        for(int i=0;i<5;i++){
//            new Thread(new SaleTicketTask(sum)).start();
//        }

        //这是同一个对象地址，如果用Integer等类型，并不是对象地址的传递
//        AtomicInteger sumAto=new AtomicInteger(10);
//        for(int i=0;i<5;i++){
//            new Thread(new SaleTicketTaskA(sumAto)).start();
//        }


        //todo 基本数据类型及对象类型，不可以当做共享对象来进行传递，也是直接传的值
        //其他的对象类型，可以实现
        Integer sum2=new Integer(5);
        log.info("main url="+System.identityHashCode(sum2));
        for(int i=0;i<2;i++){
            new Thread(new SaleTicketTaskB(sum2)).start();
        }
        log.info("start end");
    }
}
