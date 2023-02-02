package com.kuark.tool.base.javaAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * TODO 测试随机读写类RandomAccessFile，可从文件指定位置来读写文件内容
 * Created by caoqingyuan on 2017/2/28.
 */
public class RandomAessceMain {
    public static void main(String[] args) throws IOException {
        File file=new File("e:/testFile/RandomAccessFile.txt");
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        byte[] b=new byte[1024];
        int read = randomAccessFile.read(b,0,(int)file.length());
        System.out.println("sum char num="+read);
        System.out.println("data="+new String(b,"GBK"));
        /**
         * RandomAccessFile读取是以指针的方式类读取，读到指定位置，seek的位置就变成了当前读到的位置
         * 再次进行读取就是从这个位置进行的读取
         * */
        //指定文件读写位置
//        randomAccessFile.seek(file.length()-3);
//        String c="\n这是一个追加的数据，来验证是否可以在指定位置来进行写入";
//        randomAccessFile.write(c.getBytes("GBK"));
//        System.out.println("size="+file.length());
        byte[] b1=new byte[1024];
        randomAccessFile.seek(0);
        int read2=randomAccessFile.read(b1,0,(int)file.length());
        System.out.println("sum2="+read2);
        System.out.println("data2="+new String(b1,"GBK"));
    }
}
