package com.kuark.tool.base.javaAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by caoqingyuan on 2017/3/6.
 */
public class JavaUtil {
    //TODO Arrays类的用法
    public static void main1(String[] args){
        String[] a={"aa","bb","cc","dd"};
        //把数组转换为list集合的形式
        List<String> list= Arrays.asList(a);
        System.out.println(list.size());
        for(String st:list){
            System.out.println("="+st);
        }
    }
    //TODO System类的用法，用于获取一些系统参数
    public static void main(String[] args){
//        System.out.println("size="+args.length);
//        System.out.print("input data:");
//        //文本扫描器，可以从指定输入流中读取文本信息
//        Scanner scanner=new Scanner(System.in);
//        String next = scanner.next();
//        System.out.println("next="+next);
//        System.setIn(new ByteArrayInputStream("abcd".getBytes()));
//        Scanner scanner1=new Scanner(System.in);
//        String next1 = scanner1.next();
//        System.out.println("next1="+next1);
//        System.currentTimeMillis();//获取当前系统时间ms
        //控制台对象
//        Console console = System.console();
//        String readLine = console.readLine();
//        System.out.println("readLine="+readLine);
        Properties properties = System.getProperties();
        //用户账户名
        String username = properties.getProperty("user.name");
        //用户的主目录
        String userhome = properties.getProperty("user.home");
        //用户的当前工作目录
        String userdir = properties.getProperty("user.dir");
        System.out.println("userdir="+userdir);
        System.out.println("userhome="+userhome);
        System.out.println("username="+username);
    }
}
