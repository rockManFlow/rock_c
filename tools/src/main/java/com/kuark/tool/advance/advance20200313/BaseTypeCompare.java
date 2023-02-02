package com.kuark.tool.advance.advance20200313;

/**
 * @author caoqingyuan
 * @detail 基本数据类型比较
 * @date 2020/4/8 10:32
 */
public class BaseTypeCompare {
    /**
     * 任意封装类型与对应的基本数据类型相比都是会使用对应的
     * Number抽象类中的xxxValue(){例如longValue()}来进行比较真实的大小
     * 不同基本数据类型比较会进行类型自动提升
     */

    //Integer类型比较大小使用equals方法
    public static void integerTest(){
        //Integer构造参数必须是有参，因为他的value是final int来修饰的，必须进行赋值
        Integer a=new Integer(10);
        Integer n=100;
        Integer m=100;
        int m2=1000;
        System.out.println(n.equals(m));//Integer Integer相比不管大于127还是在他区间都可以
        //Integer Integer相比大于127或小于-128，这种方式比较是返回false
        //todo INteger是个特例
        System.out.println(n==m);
        System.out.println(m2==m);//true
    }

    public static void doubleTest(){
        Double d2=10000000D;
        long l2=10000000;
        int i2=10000000;
        //todo int long与Double进行比较的时候，是会自动进行类型提升，及提升到double，再进行比较值
        //todo 但需要注意，当是float类型时进行比较会丢失精度
        System.out.println("d2 l2="+(d2==l2));//todo true
        System.out.println("d2 i2="+(d2==i2));//todo true
        System.out.println("d2 l2="+(d2.equals(l2)));//todo false 看源码及知原因

        Double d3=100D;
        Double d4=100D;
        System.out.println("d3 d4="+(d3==d4));//todo false 对象比较的是地址
    }

    /**
     * 基本数据类型占用字节数
     */
    public static void maxTypeValue(){
        System.out.println("integer 4个字节 max="+Integer.MAX_VALUE);
        System.out.println("long 8字节 max=");
        System.out.println("float 4字节 max=");
        System.out.println("double 8字节 max=");
        System.out.println("char 2字节 max=");
        System.out.println("byte 1个字节 max="+"-128 127");
        System.out.println("short 2字节 max="+"-2^15 2^15-1一位符号位");
        System.out.println("boolean 1bit位 max=");
    }

    public static void main(String[] args) {
//        integerTest();
        doubleTest();
    }

}
