package com.kuark.tool.advance.advance20201105util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author caoqingyuan
 * @detail 数据操作
 * @date 2019/5/20 16:07
 */
public class DataUtil {

    public static ByteArrayOutputStream input2Output(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bou=new ByteArrayOutputStream();
        byte[] bytes=new byte[1024];
        while (inputStream.read(bytes)!=-1){
            bou.write(bytes);
        }
        bou.flush();
        inputStream.close();
        return bou;
    }

    /**
     * 把外部输入流缓存到内存中，并也当做一个输入流
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static ByteArrayInputStream copyInput2Memory(InputStream inputStream) throws IOException {
        return new ByteArrayInputStream(input2Output(inputStream).toByteArray());
    }

    public static ByteArrayOutputStream zipInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bou=new ByteArrayOutputStream();
        ZipOutputStream zou=new ZipOutputStream(bou);
        byte[] bytes=new byte[1024];
        while (inputStream.read(bytes)!=-1){
            zou.write(bytes);
        }
        zou.flush();
        inputStream.close();
        return bou;
    }

    public static String zipInfo(String info) throws IOException {
        ByteArrayOutputStream bou=null;
        ZipOutputStream zou=null;
        byte[] zouByte =null;
        try{
            bou=new ByteArrayOutputStream();
            zou=new ZipOutputStream(bou);
            zou.putNextEntry(new ZipEntry("0"));
            zou.write(info.getBytes());
            zou.closeEntry();
            zouByte = bou.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            zou.close();
            bou.close();
        }

        return new String(Base64.getEncoder().encode(zouByte));
    }

    public static String unZipInfo(String zipInfo) throws IOException {
        ByteArrayOutputStream bou=null;
        ByteArrayInputStream bin=null;
        ZipInputStream zin=null;
        byte[] toByteArray =null;
        try{
            bin=new ByteArrayInputStream(Base64.getDecoder().decode(zipInfo.getBytes()));
            zin=new ZipInputStream(bin);
            zin.getNextEntry();//定位到文件的开始处

            bou=new ByteArrayOutputStream();
            byte[] bytes=new byte[1024];
            int start;
            while ((start=zin.read(bytes))!=-1){
                bou.write(bytes,0,start);
            }
            bou.flush();
            toByteArray = bou.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bou.close();
            bin.close();
            zin.close();
        }
        return new String(toByteArray);
    }

    public static void main(String[] args) throws IOException {
        String startInfo="1111";
        String zipInfo = zipInfo(startInfo);
        System.out.println("zipInfo:"+zipInfo);

        String unZipInfo = unZipInfo(zipInfo);
        System.out.println("unZipInfo:"+unZipInfo);
        System.out.println("end");
    }
}
