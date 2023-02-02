package com.kuark.tool.advance.advance20191114.arithmetic.basetype;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author rock
 * @detail java中缓存的源码实现
 * @date 2019/11/14 21:50
 */
public class BufferTypeMain {
    public static void main(String[] args){
        String s="";
        buffer();
    }

    public static void buffer(){
        /**
         * 缓冲区的特点
         * 四个属性需要熟悉
         * 容量capacity：缓冲区能够存储的最大数据量
         * 上界limit：缓冲区第一个不能被读和写的数据，缓冲区创建的时候，limit等于capacity。当capacity为1024，limit为512，
         * 缓冲区的最大就为512，剩余的就不能读也不能写
         * 位置position：下一个要读取元素的位置
         * 标记Mark：标记用的。一个备忘位置。标记在设定前是未定义的(undefined)。使用场景是，假设缓冲区中有 10 个元素，
         * position 目前的位置为 2(也就是如果get的话是第三个元素)，现在只想发送 6 - 10 之间的缓冲数据，
         * 此时我们可以 buffer.mark(buffer.position())，
         * 即把当前的 position 记入 mark 中，然后 buffer.postion(6)，此时发送给 channel 的数据就是 6 - 10 的数据。
         * 发送完后，我们可以调用 buffer.reset() 使得 position = mark，因此这里的 mark 只是用于临时记录一下位置用的。
         */
        IntBuffer intBuffer=IntBuffer.allocate(20);
        intBuffer.put(new int[]{1,2,3,4,5,6,7,8,9,10});
        int d1 = intBuffer.get();
        int d2 = intBuffer.get();
        System.out.println("d1="+d1+"||d2="+d2);
        int position = intBuffer.position();
        System.out.println("position="+position);
        intBuffer.flip();
        int d3 = intBuffer.get();
        int d4 = intBuffer.get();
        System.out.println("d3="+d3+"||d4="+d4);
        int position1 = intBuffer.position();
        System.out.println("position1="+position1);
        //把当前position放到标记位置
        intBuffer.mark();
        intBuffer.position(6);
        int d6 = intBuffer.get();
        System.out.println("d6="+d6);
        int position6 = intBuffer.position();
        System.out.println("position6="+position6);

        int d11 = intBuffer.get(1);
        System.out.println("d11="+d11);
        int position2 = intBuffer.position();
        System.out.println("调用get(index)之后，并不会影响position位置，position2位置在="+position2);
        //恢复标记位置
        intBuffer.reset();
        int position3 = intBuffer.position();
        System.out.println("position3="+position3);
    }
}
