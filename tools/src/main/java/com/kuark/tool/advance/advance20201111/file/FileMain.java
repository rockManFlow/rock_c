package com.kuark.tool.advance.advance20201111.file;

import com.mchange.v2.uid.UidUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

/**
 * @author rock
 * @detail 文件读写--channel的map方式
 * @date 2021/7/6 15:06
 */
public class FileMain {
    public static void main(String[] args) throws IOException {
        long start=System.currentTimeMillis();
        fileWrite();
        System.out.println("\ncost:"+(System.currentTimeMillis()-start)+"ms");
    }

    /**
     * 通过fileRead和fileWrite方法可以知道可以从指定位置来存和取数据，这个也是rocketmq写文件的原理
     */
    public static void fileRead() throws IOException {
        File file=new File("/Users/opayc/products/tools/src/main/java/com/kuark/tool/advance/advance20201111/file/commitLog.log");
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        long length = randomAccessFile.length();
        System.out.println("length:"+length);
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 通过这种方式实现mmap方式的读写，position：映射文件开始的位置，与buffer的position没有任何关系，如果文件大于2G，如果写0，就会报错。
         * 及从文件什么位置开始映射--所说的大于2G的要分块读？？。size表示映射的buffer内存大小，便于存储要写的数据
         */
        MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_WRITE,20,length);
        // 使用相对位置的get()操作，读取此缓冲区“当前位置”的字节，然后递增该位置。当前位置是map的position位置
        System.out.println((char)buffer.get());
        //把
        for(int start=0;start<20;start++){
            System.out.print((char)buffer.get(start));
        }
    }

    /**
     * commitLog文件通过mmap方式来读取实现
     * mmap最大可映射文件大小是1.5-2G  大于1.5基本就需要进行分块了
     * @throws FileNotFoundException
     */
    public static void fileWrite() throws IOException {
        StringBuilder sb=new StringBuilder();

        File file=new File("/Users/opayc/products/tools/src/main/java/com/kuark/tool/advance/advance20201111/file/commitLog.log");
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        long length = randomAccessFile.length();
        System.out.println("length:"+length);

        int num=1;
        FileChannel channel = randomAccessFile.getChannel();
        for(int j=0;j<10;j++){
            for(int i=0;i<10;i++){
                sb.append(num+"&");
                num++;
            }
            sb.append("\n");
        }

        /**
         * 通过这种方式实现mmap方式的读写，position：映射文件开始的位置，与buffer的position没有任何关系，如果文件大于2G，如果写0，就会报错。
         * 及从文件什么位置开始映射--所说的大于2G的要分块读？？。size表示映射的buffer内存大小，便于存储要写的数据
         */
        /**
         * 把文件的从position开始的size大小的区域映射为内存映像文件，mode指出了 可访问该内存映像文件的方式：READ_ONLY，READ_WRITE，PRIVATE。
         */
        MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_WRITE,0,sb.length());

        /**
         * 确实是从当前位置开始累加数据
         */
        buffer.put(sb.toString().getBytes());

        /**
         * 将给定的字节写入此缓冲区的“当前位置”  当前位置是map映射的位置
         */
//        buffer.put(sb.toString().getBytes(),0,sb.length());

        /**
         * 立即将内容刷新到磁盘
         */
        buffer.force();

        /**
         * 覆盖指定位置的数据为新数据
         */
//        buffer.put(sb.toString().getBytes(),0,sb.length());
        randomAccessFile.close();
    }

    public static void readFile() throws IOException {
        //?????
        FileOutputStream out=new FileOutputStream("D:\\myProjects\\tools\\logs\\123.iso");
        FileInputStream inputStream=new FileInputStream("E:\\test\\CentOS-6.5-x86_64-bin-DVD1.iso");
        //4kb
        byte[] arr=new byte[4096];
        while (inputStream.read(arr)!=-1){
            out.write(arr);
        }
        out.flush();
        inputStream.close();
        out.close();
    }

    public static void readNioFile() throws IOException{
        FileOutputStream out=new FileOutputStream("D:\\myProjects\\tools\\logs\\1234.iso");
        FileChannel outChannel = out.getChannel();
        FileInputStream in=new FileInputStream("E:\\test\\CentOS-6.5-x86_64-bin-DVD1.iso");
        FileChannel channel = in.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (channel.read(buffer)!=-1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        channel.close();
        outChannel.close();
    }

    public static void readMmapFile()throws IOException{
        //  ???????RandomAccessFile????????rw??
        RandomAccessFile acf = new RandomAccessFile("D:\\myProjects\\tools\\logs\\12345.iso", "rw");
        FileChannel fc = acf.getChannel();
        byte[] bs = new byte[4096];
        int len = bs.length * 1000;
        long offset = 0;
        int i = 2000000;
        while(i > 0) {
            MappedByteBuffer mbuf = fc.map(FileChannel.MapMode.READ_WRITE, offset, len );
//            mbuf.put()
            for(int j = 0; j < 1000; j ++) {
                mbuf.put(bs);
            }
            offset = offset + len;
            i = i - 1000;

            //?????????????????????????????byte??????
//            mbuf.get(byte[] dst, int offset, int length)
        }
        fc.close();
    }

    public static void readFile2(String filePath){
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            //FileReader????????
            reader = new BufferedReader(new FileReader(file), 5 * 1024 * 1024);   //????????????????????
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                System.out.println(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String filePath, String fileContent) {
        File file = new File(filePath);
        // if file doesnt exists, then create it
        FileWriter fw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileContent);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
