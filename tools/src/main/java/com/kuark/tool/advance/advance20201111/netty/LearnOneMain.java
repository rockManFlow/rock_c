package com.kuark.tool.advance.advance20201111.netty;

import java.net.InetSocketAddress;

/**
 * @author rock
 * @detail 安全管理器
 * @date 2021/3/12 10:26
 */
public class LearnOneMain {
    public static void main(String[] args) {
        int num=3;
        System.out.println(num<<1);

        String channelCode="dehudehuGarbage";
        if(channelCode.contains("Garbage")){
            channelCode="Garbage";
        }
        System.out.println(channelCode);
    }

    private static void systemProperty(){
        //用户获取JVM和操作系统的一些参数信息
        System.getProperty("java.vm.version");//java虚拟机版本
        System.getProperty("java.vendor.url"); //java官方网站
        System.getProperty("java.vm.nam"); //java虚拟机名称
        System.getProperty("user.country"); //国家或地区
        System.getProperty("user.dir"); //工程的路径
        System.getProperty("java.runtime.version");//java运行环境版本
        System.getProperty("os.arch"); //操作系统位数（32或64）
        System.getProperty("os.name"); //操作系统名称
        System.getProperty("sun.jnu.encoding"); //编码格式
        System.getProperty("os.version"); //操纵系统版本
        System.getProperty("java.version"); //java版本版本
    }

    private static void securityManager(){
        /**
         * SecurityManager 应用场景
         *
         * 　　当运行未知的Java程序的时候，该程序可能有恶意代码（删除系统文件、重启系统等），
         * 为了防止运行恶意代码对系统产生影响，需要对运行的代码的权限进行控制，这时候就要启用Java安全管理器。
         * https://blog.csdn.net/weixin_30703911/article/details/95166967
         * ?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
         * &dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control
         */
    }

    private static void net(){
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);

    }


}
