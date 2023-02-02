package com.kuark.tool.advance.advance20201105util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileMain {
    private static Long position=3L;
    //一次读取1K大小
    private static long SIZE=1024L;

    public static void main(String[] args) throws IOException {
        String osType = System.getProperty("os.name");
        System.out.println(osType);
        String fileUrl=osType.contains("Window")?"D:\\myProjects\\tools\\src\\main\\resources\\file.txt":"/Users/opayc/products/tools/src/main/resources/file.txt";
//        write(fileUrl);
        write2(fileUrl);
    }

    /**
     * FileChannel的使用。从文件指定位置开始读取指定大小的数据到程序中
     */
    public static void read(String fileUrl) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileUrl);
        FileChannel channel = fileInputStream.getChannel();

        //映射数据最大不能超过文件最大数
        long size=channel.size()-position<SIZE?(channel.size()-position):SIZE;

        //从文件指定位置映射指定大小数据到缓存中---采用的是映射，直接把内核缓存中的数据通过映射可以不用copy直接操作数据
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, position, size);

        //mappedByteBuffer.limit()表示真实缓冲数据的大小
        byte[] array=new byte[mappedByteBuffer.limit()];

        //把缓冲区中的数据读取到内存中
        mappedByteBuffer.get(array);
        System.out.println(new String(array));
        channel.close();
        fileInputStream.close();
    }


    /**
     * 直接通过channel写到文件中
     * @param fileUrl
     * @throws IOException
     */
    public static void write(String fileUrl) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile(fileUrl,"rw");
        FileChannel channel = randomAccessFile.getChannel();

        String data="#testwrite#";
        ByteBuffer buffer = MappedByteBuffer.allocate(data.length());
        buffer.put(data.getBytes());

        //把缓存指针从写的位置移到缓存首位，之后才能操作这些数据
        buffer.flip();

        //从文件指定位置（文件最后）追加数据
        channel.write(buffer,channel.size());
        channel.force(false);
        channel.close();
        randomAccessFile.close();
    }

    /**
     * 通过map的方式映射写到文件中
     * @param fileUrl
     * @throws IOException
     */
    public static void write2(String fileUrl) throws IOException {
        FileChannel channel =new RandomAccessFile(fileUrl,"rw").getChannel();

        String data="#222write111#";

        MappedByteBuffer buffer =channel.map(FileChannel.MapMode.READ_WRITE,channel.size(),data.length());

        buffer.put(data.getBytes());

        channel.force(true);
        channel.close();
    }

    /**
     * 只需要读时可以使用FileInputStream，写映射文件时一定要使用随机( RandomAccessFile)访问文件
     */
}
