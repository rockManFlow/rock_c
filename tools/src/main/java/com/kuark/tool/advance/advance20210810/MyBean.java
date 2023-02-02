package com.kuark.tool.advance.advance20210810;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.servlet.Filter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author rock
 * @detail
 * @date 2021/11/2 15:33
 */
@Slf4j
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyBean {

    public void designPattern() throws FileNotFoundException {
        /**
         * jdk中使用到的设计模式
         */
        //建造者模式：（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象
        StringBuilder builder=new StringBuilder();
        builder.append("");
        StringBuffer buffer=new StringBuffer();
        buffer.append(1);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
//        byteBuffer.put();

        //工厂模式---复杂对象的创建封装在工厂中，由工厂统一实现
        Calendar calendar=Calendar.getInstance();
        NumberFormat format=NumberFormat.getInstance();

        //原型模式 clone:用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
        Object o=new Object();

        //单例模式:仅会创建一个对象
        Runtime runtime=Runtime.getRuntime();

        //适配器模式:(Adapter Pattern) ：将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作
        Arrays.asList();
//        Collections.list();

        //装饰器模式：允许向一个现有的对象添加新的功能，同时又不改变其结构
        InputStream inputStream=new FileInputStream("");

        //代理模式
//        Proxy

        //责任链模式
//        Filter
//        javax.servlet.Filter#doFilter()  通过调用链方式来调用各个filter

        //命令模式
//        Runnable

        //迭代器模式：用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
        //有hasnext来判断是否有下一个数据，next来取数据。各个实现集合已自己的方式来顺序取数据
//        Iterator、Scanner
        List<String> list=new ArrayList<>(4);
        list.add("22");
        list.add("11");
        list.add("33");
        list.add("44");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

        //观察者+策略+模板方法
        //观察者（发布订阅）
        //ApplicationEvent(spring)

        //策略:通过抽象/接口 不同的逻辑实现不同的功能

        //模板方法模式
        //OutputStream#write方法--定义统一的模板，由各个实现实现不同的功能


    }
    public static void main(String[] args) {
//        boolean equals = Boolean.TRUE.equals(null);
//        System.out.println(equals);
//        System.out.println(System.currentTimeMillis());
//        long l=System.currentTimeMillis()%1000;
//        System.out.println(l);
        log.info("",new NullPointerException("ddd"));
    }

    /**
     * luhn算法计算卡号
     * @param userId
     * @return
     */
    private static String getCardNo(String userId) {
//        String cardNo = "50612402020" + userId.substring(userId.length()-7);
        String cardNo ="506124020205041997";
        int count = 0;
        char[] chars = cardNo.toCharArray();
        for(int i = chars.length-1, j=0; i >=0;i-=2,j++){
            int num = Integer.parseInt(chars[i]+"");
            if (j % 2 == 0){
                num *=2;
                if (num >= 10){
                    count +=(num-9);
                }else {
                    count += num;
                }
            }else {
                count += num;
            }
        }
        int lastCardNo = count % 10;
        if (lastCardNo == 0){
            return cardNo + "0";
        }
        lastCardNo = 10 - lastCardNo;
        return cardNo+lastCardNo;
    }
    public void beanImpl(){
        /**
         * 对象实例化过程：包括两步 一个是类的加载  一个是对象初始化
         * 类加载：当方法区中不存在该class信息时，会触发类加载
         *  加载（classpath中加载对应类信息）
         *  验证（验证类信息是否合法）
         *  初始化（执行类构造器<clinit>初始化类信息：类静态变量及类静态代码块，JVM保证先初始化父类初始化，在执行子类初始化）
         *
         *
         *  类加载器：引导类加载器、扩展类加载器、系统类加载器   涉及到双亲委派原则
         *  引导类加载器：JVM自身需要的类，负责将java_home/lib包下的JVM指定包名加载
         *  扩展类加载器：<JAVA_HOME>/lib/ext目录下或者由系统变量-Djava.ext.dir指定位路径中的类库
         *  系统类加载器：负责加载系统类路径java -classpath或-D java.class.path 指定路径下的类库
         *
         *  双亲委派原则
         *  类的加载都是先交给父加载器来加载，父加载器加载不到，在使用子加载器加载
         *  好处：保证jdk中类的安全、减少重复加载
         *
         *  对象实例化过程
         *  执行类构造函数init
         *  父类静态变量、父类静态代码块、子类静态变量、子类静态代码块、父类成员变量、父类方法块、父类构造器、子类成员变量、子类方法块、子类构造器
         *
         *
         */
    }
}
