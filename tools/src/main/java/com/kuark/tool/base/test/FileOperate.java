package com.kuark.tool.base.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by cc on 2016/5/19.
 * TODO 读取指定路径下所有文件和文件夹信息，删除指定路径下所有文件，只删除指定路径下所有空的文件夹
 */
public class FileOperate {
    //纯盘名也可以读取
    //public static String fileUrl = "g:";
    public static String fileUrl = "D:\\test\\check-file\\out";
    //空文件夹个数
    public static int emptyFileNum=0;

    public static void main(String[] args) {
        try {
            //readfile(fileUrl);
            deletefile(fileUrl);
//            initEmptyFileNum();
//            deleteEmptyDirectory(fileUrl);
//            System.out.println("emptyFileNum="+emptyFileNum);
            System.out.println("ok");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //初始化空文件夹开始个数
    public static void initEmptyFileNum(){
        FileOperate.emptyFileNum=0;
    }
    //删除空文件夹
    public static boolean deleteFolder(File file) {
        String[] files2List2 = file.list();
        if (files2List2.length == 0) {
            file.delete();
            FileOperate.emptyFileNum++;
            return true;
        }
        return false;
    }

    //只删除指定路径下的所有空文件夹
    public static void deleteEmptyDirectory(String fileu) {
        File files = new File(fileu);
        if (!files.isDirectory()) {
            return;
        }
        String[] fileList = files.list();
        for (int i = 0; i < fileList.length; i++) {
            File files2 = new File(fileu + "/" + fileList[i]);
            if (!files2.isDirectory()) {
                continue;
            } else {
                //该文件夹为空，就直接删除
                boolean b = deleteFolder(files2);
                if (!b) {
                    deleteEmptyDirectory(fileu + "/" + fileList[i]);
                    //判断删除内层文件夹之后，该文件夹是否就变成了空
                    deleteFolder(files2);
                }
            }
        }
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
                System.out.println("====================");

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                //该路径中的所有文件和文件夹的名字
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("文件");
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());
                        System.out.println("====================");
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        System.out.println("读取文件完成");
        return true;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件(也可以删除不能在系统中删除的文件夹)
     */
    public static boolean deletefile(String delpath)
            throws FileNotFoundException, IOException {
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
//                        System.out.println("path=" + delfile.getPath());
//                        System.out.println("absolutepath="
//                                + delfile.getAbsolutePath());
//                        System.out.println("name=" + delfile.getName());
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (FileNotFoundException e) {
            System.out.println("deletefile()   Exception:" + e.getMessage());
        }
        System.out.println("删除文件成功");
        return true;
    }

}
