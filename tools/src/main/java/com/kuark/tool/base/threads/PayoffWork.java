package com.kuark.tool.base.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author caoqingyuan
 * @detail 10万笔交易1分钟之内执行完
 * @date 2019/1/9 9:59
 */
public class PayoffWork extends Thread {
    private Logger logger= LoggerFactory.getLogger(PayoffWork.class);
    //总笔数
    private static AtomicLong sumNum=new AtomicLong(0);
    public static Date endDate=null;
    @Override
    public void run(){
        long num=-1;
        synchronized (PayoffWork.class){
            num=sumNum.addAndGet(1);
            if(num==100000){
                endDate=new Date();
            }else if(num>100000){
                return;
            }
        }

        try {
            //具体发工资过程+网络延迟
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("【"+num+"】发工资完成");
    }
}
