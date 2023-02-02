package com.kuark.tool.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * TODO 弄明白ClassLoader类加载器的用法：是从classpath路径中加载配置文件的
 * classpath路径是classes和lib包所在的路径
 * Created by caoqingyuan on 2017/3/24.
 */
public class ConfigUtil {
    public static Properties loadConf(String configFileName) throws IOException {
        Properties prop=new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream;
        if(loader != null) {
            //默认会从classpath的根目录中查找文件
            inputStream = loader.getResource(configFileName).openStream();
        } else {
            inputStream = ClassLoader.getSystemResource(configFileName).openStream();
        }
        prop.load(inputStream);
        if(inputStream!=null){
            inputStream.close();
        }
        return prop;
    }

    public static void main(String[] args) throws IOException {
        //Thread.currentThread()：返回当前执行的线程对象
        //返回当前线程的上下文的类加载器对象
        //TODO ClassLoader类的用法
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String configFileName= "Test/hhhh.txt";
        URL url = null;
        if(loader != null) {
            System.out.println("nnnn");
            //默认会从classpath的根目录中查找文件
            url = loader.getResource(configFileName);
        } else {
            url = ClassLoader.getSystemResource(configFileName);
        }
        if(url!=null){
            System.out.println("getFile="+url.getFile());
        }else{
            System.out.println("aaaa");
        }
    }
//    public static void main(String[] args){
//        //方法1
//        URL url1 = ClassLoader.getSystemResource("business.properties");
//        System.out.println("url1:\t" + (url1 == null ? "null" : url1.getPath()));
//
//        URL url1withSlash = ClassLoader.getSystemResource("/business.properties");
//        System.out.println("url1/:\t" + (url1withSlash == null ? "null" : url1withSlash.getPath()));
//
//        //方法2
//        ClassLoader classLoader2 = this.getClass().getClassLoader();
//        URL url2 = classLoader2.getResource("business.properties");
//        System.out.println("url2:\t" + (url2 == null ? "null" : url2.getPath())+";classLoader is:"+classLoader2.toString());
//
//        URL url2withSlash = classLoader2.getResource("/business.properties");
//        System.out.println("url2/:\t" + (url2withSlash == null ? "null" : url2withSlash.getPath()));
//
//        //方法3
//        URL url3 = this.getClass().getResource("business.properties");
//        System.out.println("url3:\t" + (url3 == null ? "null" : url3.getPath()));
//
//        URL url3withSlash = this.getClass().getResource("/business.properties");
//        System.out.println("url3/:\t" + (url3withSlash == null ? "null" : url3withSlash.getPath()));
//
//        //方法4
//        ClassLoader classLoader4 = Thread.currentThread().getContextClassLoader();
//        URL url4 = classLoader4.getResource("business.properties");
//        System.out.println("url4:\t" + (url4 == null ? "null" : url4.getPath())+";classLoader is:"+classLoader4.toString());
//
//        URL url4withSlash = classLoader4.getResource("/business.properties");
//        System.out.println("url4/:\t" + (url4withSlash == null ? "null" : url4withSlash.getPath()));
//    }
}
