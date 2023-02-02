package com.kuark.tool.advance.advance20190708;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RandomAccessFileMain {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("/Users/opayc/products/tools/src/main/java/com/kuark/tool/advance/Test.txt"
                ,"rw");

        //设置文件读写位置
        randomAccessFile.seek(4);

        //方法1：放到数组中
//        byte[] arrays=new byte[4];
//        while (randomAccessFile.read(arrays)!=-1){
//            //中文是2-4个字节，字节数组是4个，输出可能会出现乱码，由于位数不一致
//            System.out.println(new String(arrays));
//        }

        Charset charset = Charset.defaultCharset();
        System.out.println("project encoding="+charset.name());

        //方法2：使用readLine读一行（读取出来的都是使用ISO-8859-1编码的，需要转下码）
        //RandomAccessFile 读写文件时，不管文件中保存的数据编码格式是什么 使用 RandomAccessFile对象方法的 readLine()
        // 都会将编码格式转换成 ISO-8859-1 所以 输出显示是还要在进行一次转码
        String data=null;
        while ((data=randomAccessFile.readLine())!=null){
            //转码
            String str = new String(data.getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(str);
        }

        //写数据到文件中  write可以保证写入的不乱码.中文默认格式都是GBK。
        /**
         * 为什么中文是GBK呢在程序中写的？
         * “ Java 源代码-> Java 字节码”，标准的 Java 编译器 javac 使用的字符集是系统默认的字符集，
         * 比如在中文 Windows 操作系统上就是 GBK ,而在 Linux 操作系统上就是ISO-8859-1，
         * 所以大家会发现在 Linux 操作系统上编译的类中源文件中的中文字符都出了问题，解决的办法就是在编译的时候添加 encoding 参数，这样才能够与平台无关。
         * 用法是  javac ?Cencoding GBK。
         *
         * 原来是与编译器所在的平台编码格式有关系
         */
        randomAccessFile.write("中国".getBytes("GBK"));
        randomAccessFile.writeBytes("world");
        randomAccessFile.close();
    }

    /**
     * 转码
     * @throws UnsupportedEncodingException
     */
    public static void transcode() throws UnsupportedEncodingException {
        //比如：data输出是通过ISO-8859-1格式输出的，想转成UTF-8，可以使用下面的这个来转码
        String data="";
        //解释：data按照编码格式ISO-8859-1转成byte字节，字节这时是没错的并且没编码格式区别，之后按照UTF-8编码（及几个字节组装成一个字符在字符串中存储）
        String str = new String(data.getBytes("ISO-8859-1"),"UTF-8");
    }
}
