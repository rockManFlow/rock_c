package com.kuark.tool.base.javaAPI.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO nio类的一些新特性的测试及方法的使用
 * Created by caoqingyuan on 2017/2/27.
 */
public class NioTest {
    private static final int BSIZE = 1024;
    //TODO channel的基本读写用法

    /**
     * 通道和缓冲区的主要关系是：buffer与channel相互的读写
     * 一下是基本操作
     * @param args
     * @throws IOException
     */
    public static void main1(String[] args) throws IOException {
        System.out.println("start");
        FileChannel fc = new FileOutputStream("e:/testFile/nioTest1.txt").getChannel();
        //首次写入的信息
        String data="这是一个测试nio的数据，测试内容为：test xml 123";
        fc.write(ByteBuffer.wrap(data.getBytes()));//把缓冲区中的数据写入到通道中
        fc.close();//关闭通道

        //RandomAccessFile这个类可以从指定位置来读取文件或者写入文件
        fc = new RandomAccessFile("e:/testFile/nioTest1.txt", "rw").getChannel();
        fc.position(fc.size());
        //之后追加的信息
        String zhuijiaData="\nSome more infomation read file";
        fc.write(ByteBuffer.wrap(zhuijiaData.getBytes()));
        fc.close();
        System.out.println("===============");
        //进行读取的信息
        fc = new FileInputStream("e:/testFile/nioTest1.txt").getChannel();
        //TODO 通道默认起始位置为0
        ByteBuffer buff = ByteBuffer.allocate(BSIZE); // 分配新的字节缓冲区
        fc.read(buff);  // 从“通道”向缓冲区中读写数据，这个是以指针的方式来移动的
        buff.flip();  // TODO 翻转此缓冲区，将Buffer从写模式切换到读模式，反之也行
        while (buff.hasRemaining()) {//判断是否还有元素
            System.out.print((char)buff.get());
        }
        fc.close();
        System.out.println("\nend");
    }
    //TODO 多线程测试nio非堵塞的访问
    public static void main2(String[] args){
        System.out.println("main start");
        String url="e:/testFile/nioTest1.txt";
        try {
            final FileChannel channel=new FileInputStream(url).getChannel();
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    System.out.println("start1");
                    ByteBuffer bb=ByteBuffer.allocate(1024);//分配一个新的字节缓冲区
                    try {
                        channel.read(bb);
                        int num=bb.position();
                        bb.flip();//翻转--把指针从最后位置翻转到开始位置
                        Thread.sleep(3*1000);
                        byte[] bt=new byte[1024];
                        bb.get(bt,0,num);
                        System.out.println("11="+new String(bt));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end1");
                }
            };
            Thread th=new Thread(runnable);
            th.start();
            Thread th2=new Thread(){
              public void run(){
                  System.out.println("start2");
                  ByteBuffer bb=ByteBuffer.allocate(1024);//分配一个新的字节缓冲区
                  try {
                      channel.position(0);
                      channel.read(bb);
                      int position=bb.position();
                      System.out.println("position="+position);
                      bb.flip();//翻转--把指针从最后位置翻转到开始位置
                      System.out.println("end posi="+bb.position());
                      byte[] bt=new byte[1024];
                      //判断是否还有元素bb.hasRemaining()中只能获取一个字节
                      bb.get(bt,0,position);
                      System.out.println("22="+new String(bt));
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  System.out.println("end2");
              }
            };
            th2.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
    //TODO 测试io阻塞访问的特点，来反映出nio的特点和必要性
    //TODO 网络中的io流本身就一个，如果有多个线程需要访问这个io流对象，就会出现一个线程访问，其他的线程阻塞访问不到
    public static void main(String[] args) throws FileNotFoundException {
        String url="e:/testFile/nioTest1.txt";
        System.out.println("main start");
        final FileInputStream fileInputStream=new FileInputStream(url);
        new Thread(){
            public void run(){
                System.out.println("thread start1");
                byte[] bb=new byte[1024];
                try {
                    fileInputStream.read(bb);
                    Thread.sleep(3*1000);
                    System.out.println("aa="+new String(bb));
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end1");
            }
        }.start();
        System.out.println("main middle");
        final FileInputStream fileInputStream2=new FileInputStream(url);
        new Thread(){
            public void run(){
                System.out.println("thread start2");
//                try {
//                    Thread.sleep(6*1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                byte[] bb=new byte[1024];
                try {
                    fileInputStream2.read(bb);
                    System.out.println("bb="+new String(bb));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end2");
            }
        }.start();
        System.out.println("main end");
    }
}
