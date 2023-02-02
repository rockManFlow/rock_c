package com.kuark.tool.base.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * Created by cc on 2016/4/28.
 * 监控指定文件中监控字段出现的次数
 */
@Component
public class PaymentLogTask {
    private static final Logger logger= Logger.getLogger(PaymentLogTask.class);

    private static long lastTimeFileSize = 0;  //上次文件大小
    private String validate1="com.kuark.payment.controller.ServiceEntryController";
    private String validate2="com.kuark.payment.controller.CupeCallBackFrontController";
    private String validate3="com.kuark.payment.queue.PayInTriggerJob";
    private String urlFile="e:/payment2.log";

    @Scheduled(cron="0 0/1 * * * ?")
    //@Test
    public void readFile(){
        logger.info("test");
//        File file=new File(urlFile);
//
//        try {
//            final RandomAccessFile randomFile = new RandomAccessFile(file, "r");
//            int validate1Count=readFile(file,validate1,lastTimeFileSize,randomFile);
//            int validate2Count=readFile(file,validate2,lastTimeFileSize,randomFile);
//            int validate3Count=readFile(file,validate3,lastTimeFileSize,randomFile);
//            System.out.println("validate1 count="+validate1Count);
//            System.out.println("validate2 count="+validate2Count);
//            System.out.println("validate3 count="+validate3Count);
//        } catch (FileNotFoundException e) {
//            logger.error("文件没有找到",e);
//        }
    }
    public int readFile(File file,String validate,long lastTimeFileSize,RandomAccessFile randomFile) throws FileNotFoundException {
        System.out.println(validate+" read start");
        int counts=0;
        //指定文件可读可写
        try {
            //获得变化部分的起始位置
            randomFile.seek(lastTimeFileSize);
            String tmp = "";
            while ((tmp = randomFile.readLine()) != null) {
                int res=tmp.indexOf(validate);
                if(res!=-1){
                    counts++;
                }
            }
            lastTimeFileSize = randomFile.length();
            System.out.println(validate+" size="+lastTimeFileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(validate+" read end");
        return counts;
    }
}
