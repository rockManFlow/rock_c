package com.kuark.tool.advance.advance20200313;

import java.io.File;

/**
 * @author rock
 * @detail todo 注意file.list()和file.listFiles()获取的是目录下的所有文件和文件夹，如果是文件路径，返回的是Null，该文件下没文件及文件夹
 * @date 2020/4/21 17:58
 */
public class FileTest {
    public static void main(String[] args) {

        //创建File对象
        File file = new File("D:\\Android");
        //获取该目录下的所有文件
        String[] files = file.list();

        for (String f : files){
            System.out.println(f);
        }

        //listFiles是获取该目录下所有文件和目录的绝对路径
        File[] fs = file.listFiles();
        for (File f : fs){
            System.out.println(f);
        }
    }
}
