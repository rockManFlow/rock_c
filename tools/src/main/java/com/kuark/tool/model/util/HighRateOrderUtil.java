package com.kuark.tool.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高并发条件下订单号生成
 * Created by caoqingyuan on 2018/1/12.
 */
public class HighRateOrderUtil {
    private static final AtomicInteger atomicCount=new AtomicInteger(0);

    private static final Integer maxNum=50;

    /**
     * 单节点中每毫秒最大可以产生多少的交易数
     */
    public static String getSerialId(){
        StringBuilder serialId=new StringBuilder();
        serialId.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        serialId.append(atomicPolish(String.valueOf(maxNum).length(),atomicSafeAdd()));
        return serialId.toString();
    }

    /**
     * 自动安全累加
     * @return
     */
    private static synchronized Integer atomicSafeAdd(){
        int i = atomicCount.incrementAndGet();
        if(i==maxNum){
            atomicCount.set(0);
        }
        return i;
    }

    /**
     * 自动以0来补齐指定位
     * @param digit 总位数
     * @param paramNum 需要补齐的参数
     * @return  补齐后的值
     */
    private static String atomicPolish(int digit,Integer paramNum){
        int paramLength = String.valueOf(paramNum).length();
        if(paramLength>digit){
            throw new IllegalArgumentException("设定补齐位数不合法");
        }
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<(digit-paramLength);i++){
            buffer.append(0);
        }
        return buffer.append(paramNum).toString();
    }

    public static void main(String[] args){
        //多线程安全测试
        for(int i=0;i<50;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(getSerialId());
                }
            }).start();
        }
    }
}
