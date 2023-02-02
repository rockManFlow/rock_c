package com.kuark.tool.advance.advance20201105util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author rock
 * @detail 多个文件压缩成一个压缩文件
 * @date 2021/10/18 18:21
 */
public class FileZipUtil {
    public static void main(String[] args) throws IOException {
        File[] sourceFile=new File[]{new File("E:\\source\\auth.txt"),new File("E:\\source\\aaaa.md"),new File("E:\\source\\bbbb.txt")};
        File zipFile=new File("E:\\source\\zipTest.zip");
        zipFiles(sourceFile,zipFile);
    }

    /**
     * 流重复读取操作
     * @throws IOException
     */
    public static void mulStream() throws IOException {
        File[] sourceFile=new File[]{new File("E:\\source\\auth.txt"),new File("E:\\source\\aaaa.md"),new File("E:\\source\\bbbb.txt")};
        //        FileInputStream inputStream=new FileInputStream(sourceFile[0]);
//        int len=0;
//        byte[] buffer=new byte[1024];
//        while ((len=inputStream.read(buffer))>0){
//            System.out.println(new String(buffer));
//        }

        //流不可以重复读取
//        int len2=0;
//        byte[] buffer2=new byte[1024];
//        while ((len2=inputStream.read(buffer2))>0){
//            System.out.println("-----------------");
//            System.out.println(new String(buffer2));
//        }

        //如何可以重复读取
        FileInputStream inputStream2=new FileInputStream(sourceFile[0]);
        //注意：缓冲区大小是有限制的，当文件过大，重复使用就有可能使用不了，因为文件的大小，已经超出mark的范围，再执行reset就会不好用
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream2);
        bufferedInputStream.mark(bufferedInputStream.available()+1);
        int len2=0;
        byte[] buffer2=new byte[1024];
        while ((len2=bufferedInputStream.read(buffer2))>0){
            System.out.println(new String(buffer2));
        }

        System.out.println("=============================");
        bufferedInputStream.reset();
        while ((len2=bufferedInputStream.read(buffer2))>0){
            System.out.println(new String(buffer2));
        }
    }

    /**
     * 多个文件流压缩成一个压缩文件
     * @param sourceFiles
     * @param zipFile
     * @throws IOException
     */
    public static void zipFiles(File[] sourceFiles,File zipFile) throws IOException {
        FileOutputStream outputStream=new FileOutputStream(zipFile);
        ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream);
        ZipEntry zipEntry=null;
        for(int i=0;i<sourceFiles.length;i++){
            FileInputStream inputStream=new FileInputStream(sourceFiles[i]);
            zipEntry=new ZipEntry(sourceFiles[i].getName());
            zipOutputStream.putNextEntry(zipEntry);
            int len=0;
            byte[] buffer=new byte[1024];
            while ((len=inputStream.read(buffer))>0){
                zipOutputStream.write(buffer,0,len);
            }
            inputStream.close();
        }
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        outputStream.close();
    }
}
