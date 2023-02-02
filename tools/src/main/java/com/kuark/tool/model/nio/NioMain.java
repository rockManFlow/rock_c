package com.kuark.tool.model.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class NioMain {
    public static void main(String[] s) throws IOException {
        //Buffer 缓冲区 一个特定数据类型的基本容器，底层就是已数组实现的
//        IntBuffer allocate = IntBuffer.allocate(3);
//        allocate.put(222);
//        allocate.put(10);
//        allocate.put(19);
//        allocate.put(30);
//        allocate.flip();//翻转当前位置
//        int i = allocate.get(0);//获取当前位置的数据
//        int j = allocate.get(2);
//        System.out.println("i="+i+"|j="+j);
//        System.out.println("end");
        FileInputStream read=new FileInputStream("e:/aaaa.txt");
        FileChannel channel = read.getChannel();

    }
}
