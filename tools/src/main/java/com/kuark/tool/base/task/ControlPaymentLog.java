package com.kuark.tool.base.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by cc on 2016/4/28.
 */
public class ControlPaymentLog {

    public int readFile(File file,String validate,long lastTimeFileSize) throws FileNotFoundException {
        System.out.println(validate+" read start");
        int counts=0;
        //指定文件可读可写
        final RandomAccessFile randomFile = new RandomAccessFile(file, "r");
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
            System.out.println("number="+counts);
            lastTimeFileSize = randomFile.length();
            System.out.println(validate+" size="+lastTimeFileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(validate+" read end");
        return counts;
    }
//    public static void main(String[] s){
//        ControlPaymentLog c=new ControlPaymentLog();
//        File file=new File("e:/payment.log");
//        try {
//            c.readFile(file);
//        } catch (FileNotFoundException e) {
//            System.out.println("文件没有找到异常");
//        }
//    }
}
