package com.kuark.tool.advance.advance20230201split;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

public class SplitProcess {
    private static AtomicInteger NO=new AtomicInteger(0);
    private static long fileLength=-1L;


    private static boolean writeNewFile(String filePath,byte[] data) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile(filePath,"rw");
        randomAccessFile.write(data);
        randomAccessFile.close();

        System.out.println("writeNewFile finish filePath:"+filePath);
        return true;
    }

    private synchronized static byte[] readFile(String filePath) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile(filePath,"r");
        if(fileLength==-1L){
            fileLength=randomAccessFile.length();
            System.out.println("fileLength:"+fileLength);
        }

        if(SplitConfig.START_LOCATION>=fileLength){
            return null;
        }

        randomAccessFile.seek(SplitConfig.START_LOCATION);


        //计算数据真实大小
        int arraySize=SplitConfig.MIN_SPLIT_SIZE;
        if((SplitConfig.START_LOCATION+SplitConfig.MIN_SPLIT_SIZE)>fileLength){
            arraySize=(int)(fileLength-SplitConfig.START_LOCATION);
        }

        byte[] data=new byte[arraySize];
        randomAccessFile.read(data);
        randomAccessFile.close();

        //更新开始位置
        SplitConfig.START_LOCATION=SplitConfig.START_LOCATION+arraySize;

        return data;
    }

    public static void split() throws IOException {
        //首先判断目标文件夹是否存在
        initFolder();

        int splitFileNum=0;
        //进行逐步读取
        byte[] data;
        while ((data=readFile(SplitConfig.SOURCE_FILE_PATH))!=null){
            System.out.println("start split num:"+splitFileNum);
            //生成文件名
            String newFilePath = generateNewFilePath();

            //写新文件
            writeNewFile(newFilePath,data);

            //计数参数累加
            ++splitFileNum;

            if(splitFileNum>60){
                System.out.println("process error,exit");
                break;
            }
        }

        System.out.println("file split finish,split num:"+splitFileNum);
    }


    private synchronized static String generateNewFilePath(){
        //
        if(StringUtils.isBlank(SplitConfig.TARGET_FILE_NAME_PRE)){
            SplitConfig.TARGET_FILE_NAME_PRE=System.currentTimeMillis()+"";
        }

        StringBuilder sb=new StringBuilder(SplitConfig.TARGET_FILE_FOLDER);
        sb.append(SplitConfig.TARGET_FILE_NAME_PRE).append("_").append(NO.incrementAndGet())
                .append(SplitConfig.SOURCE_FILE_PATH.substring(SplitConfig.SOURCE_FILE_PATH.lastIndexOf(".")));

        return sb.toString();
    }


    private static void initFolder(){
        File file=new File(SplitConfig.TARGET_FILE_FOLDER);
        if(!file.exists()&&file.isDirectory()){
            //是文件夹并且不存在,创建多重文件夹
            file.mkdirs();
        }
    }
}
