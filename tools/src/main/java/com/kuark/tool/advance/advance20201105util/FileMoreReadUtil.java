package com.kuark.tool.advance.advance20201105util;


import java.io.*;

/**
 * @author caoqingyuan
 * @detail 分批次读取文件并处理  这种方式处理文件效率较慢
 * 4G
 * 每次大小1024 耗时：163s
 * @date 2019/4/28 18:16
 */
public class FileMoreReadUtil {
    private static final Integer SIZE=2048;
    private BufferedInputStream inputStream=null;
    private BufferedOutputStream outputStream=null;
    private byte[] arrays=null;

    public FileMoreReadUtil(String inFileUrl, String outFileUrl){
        try {
            inputStream = new BufferedInputStream(new FileInputStream(inFileUrl));
            outputStream = new BufferedOutputStream(new FileOutputStream(outFileUrl));
        }catch (FileNotFoundException e){
            throw new RuntimeException("file url is not exist");
        }
    }

    public void readFile() throws IOException {
        arrays=new byte[SIZE];
        int size = inputStream.read(arrays);
        while (size!=-1){
            //分批次写到新文件中
            writeFile(arrays);
            arrays=new byte[SIZE];
            size = inputStream.read(arrays);
        }
    }

    public void writeFile(byte[] arrays) throws IOException {
        outputStream.write(arrays);
        outputStream.flush();
    }

    public void close() throws IOException {
        if(inputStream!=null){
            inputStream.close();
        }
        if(outputStream!=null){
            outputStream.close();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("start main");
        long start=System.currentTimeMillis();
        FileMoreReadUtil fileMoreReadUtil =new FileMoreReadUtil("E:\\test\\CentOS-6.5-x86_64-bin-DVD1.iso","E:\\test\\333.iso");
        fileMoreReadUtil.readFile();
        fileMoreReadUtil.close();
        System.out.println("end main cost:"+(System.currentTimeMillis()-start)/1000+"s");
    }
}
