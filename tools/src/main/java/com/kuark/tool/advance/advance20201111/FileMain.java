package com.kuark.tool.advance.advance20201111;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * @author rock
 * @detail 获取文件通道，来逐步读取通道中的数据到buffer中
 * RandomAccessFile 读取文件
 * @date 2021/3/19 18:15
 */
public class FileMain {
    public static void main1(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("D:\\myProjects\\tools\\src\\main\\java\\com\\kuark\\tool\\advance\\advance20201111\\hs_err_pid1842.log", "rw");
        FileChannel fileChannel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(128);
        int size;

        while ((size=fileChannel.read(buffer))!=-1){
            buffer.flip();
            if(size<128){
                byte[] data=new byte[128];
                buffer.get(data,0,size);
                System.out.println(new String(data));
                continue;
            }
            System.out.println(new String(buffer.array()));
            //并不能清除其中的数据，仅是清除
            buffer.clear();
        }
        fileChannel.close();
    }

    public static void readFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\myProjects\\tools\\src\\main\\resources\\PROVINCES2.txt", "rw");
        String msg;
        while ((msg=randomAccessFile.readLine())!=null){
            System.out.println(msg);
        }
        randomAccessFile.close();
    }

    /**
     * 通过映射方式来减少io次数，提高磁盘文件读写效率
     * @throws IOException
     */
    public static void appendData() throws IOException {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<10;i++){
            sb.append("map"+i+"|");
        }
        byte[] bytes = sb.toString().getBytes();


        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\myProjects\\tools\\src\\main\\resources\\PROVINCES2.txt", "rw");
        long fileSize = randomAccessFile.length();
        FileChannel fileChannel = randomAccessFile.getChannel();
        //把文件映射到虚拟内存(逻辑内存：物理内存+堆外内存)，通常情况可以映射整个文件，如果文件比较大，可以进行分段映射
        //      --映射到了虚拟内存中，当内核内存进行加载信息到内核内存，再通过内核内存与用户内存进行映射
        //map0()函数返回一个地址address，这样就无需调用read或write方法对文件进行读写，通过address就能够操作文件
        //仅进行了一次的内存读写

        //追加数据需要映射当前文件大小需要放的大小
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, fileSize, bytes.length);

        //为什么这种方式并不是实时刷新到磁盘的??
//        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
//        ByteBuffer byteBuffer = map.slice();
//        byteBuffer.put(bytes,0,bytes.length);
//        byteBuffer.flip();
//
//        map.put(byteBuffer);
//        map.force();


        map.put(bytes);
        map.force();
        fileChannel.close();
        randomAccessFile.close();
    }

    public static void main(String[] args) throws IOException {
//        readFile();
//        appendData();
//        new ArrayList<>(25);
        sale(3);
    }

    public static void sale(int num){
        int jiangli=num/3;
        int sellNum=num-jiangli;
        BigDecimal multiply = new BigDecimal("4.5").multiply(new BigDecimal(sellNum));
        System.out.println(multiply);
    }
}
