package com.kuark.tool.advance.advance20201105util.formal;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.*;

/**
 * 文件或者文件夹的压缩或解压缩工具
 */
public class ZipTool {
    private static final Logger logger=Logger.getLogger(ZipTool.class);
    /****解压文件---可以直接使用的*/
    public static void unzip(File inFile,File outFile) throws IOException{
        ZipFile zipFile = new ZipFile(inFile, "GBK");
        Enumeration<ZipEntry> em = zipFile.getEntries();
        byte[] buf = new byte[1024*1024];
        while(em.hasMoreElements()){
            ZipEntry ze = em.nextElement();
            File outItemFile = new File(outFile,ze.getName());
            if(ze.isDirectory()){
                outItemFile.mkdirs();
            }else{
                InputStream is = zipFile.getInputStream(ze);
                try{
                    outFile(outItemFile,is,buf);
                } finally{
                    is.close();
                }
            }
        }
    }

    private static void outFile(File outItemFile, InputStream is, byte[] buf) throws IOException {
        outItemFile.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(outItemFile);
        try{
            int len;
            while((len=is.read(buf))!=-1){
                fos.write(buf, 0, len);
            }
        } finally{
            fos.close();
        }
    }

    /****压缩文件*****/
    /*压缩原理：
    * 注意压缩是只能压缩文件，不能直接压缩文件夹，文件夹相当于一个空的文件。
    * 需要把文件夹中的所有文件包括文件夹的路径都找出来，
    * 文件夹路径，只是放到zip文件中显示为文件夹，而文件则需要进行zip读取到zip的文件中去，
    * 不能仅放到zip文件中就可以了。
    * */
    //压缩一个文件夹（包括文件和文件夹），这个比较通用，可以压缩任何文件
    public static void zip1(File file,File targetFile) throws IOException {
        java.util.zip.ZipOutputStream zipOutputStream =null;
        FileOutputStream fileOutputStream =null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            zipOutputStream = new java.util.zip.ZipOutputStream(fileOutputStream);
            File[] files=new File[]{file};
            files=eachFiles(files);
            for(int i=1;i<files.length;i++){
                zipFile1(files[i],zipOutputStream);
            }
        }catch (IOException e){
            logger.error(e);
        }finally {
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
        }
    }
    public static void zipFile1(File file, java.util.zip.ZipOutputStream zipOutputStream) throws IOException {
        //把需要去掉的路径给去掉就可以正常的压缩了
        String filename= file.getAbsolutePath().replace("e:\\","");
        if(file.isDirectory()){
            filename+="/";
        }
        //要压缩的条目名，文件是文件名(即压缩文件中文件名+文件格式)
        System.out.println("filename="+filename);
        java.util.zip.ZipEntry ze = new java.util.zip.ZipEntry(filename);
        zipOutputStream.putNextEntry(ze);
        FileInputStream fileInputStream =null;
        try {
            if (file.isFile()) {
                fileInputStream = new FileInputStream(file);
                byte[] b = new byte[1024];
                int len;
                while ((len = fileInputStream.read(b)) != -1) {
                    zipOutputStream.write(b, 0, len);
                }
            }
        }catch (Exception e){
            logger.error(e);
        }finally {
            if(fileInputStream!=null){
                fileInputStream.close();
            }
            if(zipOutputStream!=null){
                zipOutputStream.closeEntry();
            }
        }
    }
    //获取文件夹下所有的文件和文件夹的路径
    private static File[] eachFiles(File[] files) {
        logger.info("获取指定路径中所有的文件或者文件夹");
        List<File> list = new ArrayList<File>();
        LinkedList<File> tasks = new LinkedList<File>(Arrays.asList(files));
        while( !tasks.isEmpty() ) {
            File task = tasks.remove();
            list.add(task);
            if( !task.isDirectory() ) {
                continue;
            }
            for( File c : task.listFiles() ) {
                tasks.add(c);
            }
        }
        return list.toArray(new File[0]);
    }
}
