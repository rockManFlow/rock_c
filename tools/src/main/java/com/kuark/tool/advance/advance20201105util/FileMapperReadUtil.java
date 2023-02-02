package com.kuark.tool.advance.advance20201105util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author rock
 * @detail
 * 内存映射文件来读取大的文件，需要注意：内存映射由于映射的文件长度不能超过java中int类型的最大值，所以只能处理2GB以下的文件
 * MappedByteBuffer的确快，但也存在一些问题，主要就是内存占用和文件关闭等不确定问题。
 * 被MappedByteBuffer打开的文件只有在垃圾收集时才会被关闭，而这个点是不确定的
 *
 * 光读取：耗时15s
 * 读取之后，使用通道来写就是124s，所以通过内存映射还是很快
 * @date 2020/10/13 17:49
 */
public class FileMapperReadUtil {
    //一次20M大小
    private static final Integer SIZE=20*1024*1024;
    private MappedByteBuffer[] mappedBufArrayIn;
    private int number;
    private FileInputStream fileIn;
    private FileOutputStream outputStream=null;
    private long fileLength;
    private byte[] array;

    public FileMapperReadUtil(String inFileUrl,String outFileUrl) throws IOException {
        this.outputStream = new FileOutputStream(outFileUrl);
        this.fileIn = new FileInputStream(inFileUrl);
        FileChannel fileChannel = fileIn.getChannel();

        this.fileLength = fileChannel.size();
        this.number = (int) Math.ceil((double) fileLength / (double) Integer.MAX_VALUE);
        this.mappedBufArrayIn = new MappedByteBuffer[number];// 内存文件映射数组
        long preLength = 0;//起始位置
        long regionSize = (long) Integer.MAX_VALUE;// 映射区域的大小
        for (int i = 0; i < number; i++) {// 将文件的连续区域映射到内存文件映射数组中
            if (fileLength - preLength < (long) Integer.MAX_VALUE) {
                regionSize = fileLength - preLength;// 最后一片区域的大小
            }
            mappedBufArrayIn[i] = fileChannel.map(FileChannel.MapMode.READ_ONLY, preLength, regionSize);
            preLength += regionSize;// 下一片区域的开始
        }
    }

    public void readFile() throws IOException {
        int count = 0;

        int size=0;
        while (size!=-1){
            if (count >= number) {
                size=-1;
                continue;
            }
            int limit = mappedBufArrayIn[count].limit();
            int position = mappedBufArrayIn[count].position();
            if (limit - position > SIZE) {
                array = new byte[SIZE];
                mappedBufArrayIn[count].get(array);
//                writeFile(mappedBufArrayIn[count].get(array));
                size=SIZE;
            } else {// 本内存文件映射最后一次读取数据
                array = new byte[limit - position];
                mappedBufArrayIn[count].get(array);
//                writeFile(mappedBufArrayIn[count].get(array));
                if (count < number) {
                    count++;// 转换到下一个内存文件映射
                }
                size=limit - position;
            }
        }

    }

    public void writeFile(ByteBuffer byteBuf) throws IOException {
        FileChannel outputStreamChannel = outputStream.getChannel();
        outputStreamChannel.write(byteBuf);
    }

    public void close() throws IOException {
        if(fileIn!=null){
            fileIn.close();
        }
        if(outputStream!=null){
            outputStream.getChannel().close();
            outputStream.flush();
            outputStream.close();
        }
        array = null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("start main");
        long start=System.currentTimeMillis();
        FileMapperReadUtil fileMoreReadUtil =new FileMapperReadUtil("E:\\test\\CentOS-6.5-x86_64-bin-DVD1.iso","E:\\test\\channel555.iso");
        fileMoreReadUtil.readFile();
        fileMoreReadUtil.close();
        System.out.println("end main cost:"+(System.currentTimeMillis()-start)/1000+"s");
    }
}
