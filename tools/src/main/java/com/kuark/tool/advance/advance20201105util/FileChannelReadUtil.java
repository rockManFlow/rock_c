package com.kuark.tool.advance.advance20201105util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author rock
 * @detail
 * 4G
 * 每次大小65535 耗时：82s
 * 每次大小1024  耗时：115s
 * @date 2020/10/13 17:14
 */
public class FileChannelReadUtil {

    //一次1M大小
    private static final Integer SIZE=1*2*1024;
    private FileInputStream inputStream=null;
    private FileOutputStream outputStream=null;
    private ByteBuffer byteBuf;

    public FileChannelReadUtil(String inFileUrl, String outFileUrl){
        try {
            inputStream = new FileInputStream(inFileUrl);
            outputStream = new FileOutputStream(outFileUrl);
            this.byteBuf = ByteBuffer.allocate(SIZE);
        }catch (FileNotFoundException e){
            throw new RuntimeException("file url is not exist");
        }
    }

    public void readFile() throws IOException {
        FileChannel channel = inputStream.getChannel();
        // 读取到ByteBuffer中
        int size =channel.read(byteBuf);
        while (size!=-1){
            //指针位置放到初始位置
            byteBuf.flip();
            //分批次写到新文件中
            writeFile(byteBuf);
            //清空缓存
            byteBuf.clear();
            size = channel.read(byteBuf);
        }
    }

    public void writeFile(ByteBuffer byteBuf) throws IOException {
        FileChannel outputStreamChannel = outputStream.getChannel();
        outputStreamChannel.write(byteBuf);
    }

    public void close() throws IOException {
        if(inputStream!=null){
            inputStream.getChannel().close();
            inputStream.close();
        }
        if(outputStream!=null){
            outputStream.getChannel().close();
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("start main");
        long start=System.currentTimeMillis();
        FileMoreReadUtil fileMoreReadUtil =new FileMoreReadUtil("E:\\test\\CentOS-6.5-x86_64-bin-DVD1.iso","E:\\test\\channel444.iso");
        fileMoreReadUtil.readFile();
        fileMoreReadUtil.close();
        System.out.println("end main cost:"+(System.currentTimeMillis()-start)/1000+"s");
    }
}
