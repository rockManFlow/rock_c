package com.kuark.tool.advance.advance20201111.apollo;

import java.util.ServiceLoader;

/**
 * @author rock
 * @detail
 * @date 2021/7/14 20:31
 */
public class ApolloMain {
    public static void main(String[] args) {
        //获取系统属性 -Dapollo.configService=1111  启动参数
        String configServices = System.getProperty("apollo.configService");

        //获取的是系统中环境变量的指定参数值
        configServices = System.getenv("APOLLO_CONFIGSERVICE");

        work(ServiceInterface.class);

        System.out.println(System.currentTimeMillis()/1000);
    }

    // SPI
    public static void work(Class clazz){
        ServiceLoader<ServiceInterface> loader = ServiceLoader.load(clazz);

        ServiceInterface next = loader.iterator().next();
        String show = next.show();
        System.out.println(show);

        /**
         * SPI的全名为Service Provider Interface,当服务的提供者，提供了服务接口的一种实现之后，
         * 在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。
         * 该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，
         * 就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，
         * 并装载实例化，完成模块的注入。
         *
         * 实现的加载就是通过ServiceLoader.load(clazz)实现
         */
    }
}
