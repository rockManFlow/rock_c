package com.kuark.tool.advance.advance20201111.clone;

/**
 * @author rock
 * @detail
 * 为什么需要clone对象，为什么不new
 * 1、new基本是初始化对象的值，而clone是对象当前的已修改状态-当前值
 * 2、new出来，再通过set来赋值，比较麻烦。而且clone方式是个native方法，执行比较快
 *
 * 浅克隆和深克隆区别？
 * 主要区别是否支持引用类型成员变量的复制
 *
 * 浅克隆：仅可以复制基本类型
 * 一般步骤是（浅克隆）：
 * 1. 被复制的类需要实现Clonenable接口（不实现的话在调用clone方法会抛出CloneNotSupportedException异常)， 该接口为标记接口(不含任何方法)
 * 2. 覆盖clone()方法，访问修饰符设为public。方法中调用super.clone()方法得到需要的复制对象。（native为本地方法)
 * 引用对象仅是复制的引用地址，并不是新开辟的空间
 *
 * 深克隆
 * 引用参数都实现clone接口，当引用参数多的时候，还是比较麻烦的
 * 还可以使用序列化流读取的方式来实现深克隆
 * @date 2021/6/2 11:25
 */
public class CloneMain {
    public static void main(String[] args) {
        CloneVo v1=new CloneVo();
        v1.setAddr("beijing");
        v1.setAge(20);
        v1.setName("xiaohong");
        ParamCloneVo pc=new ParamCloneVo();
        pc.setDetail("shanghai");
        pc.setPrice(10);
        v1.setParamCloneVo(pc);

        CloneVo v2=v1.clone();
        v2.getParamCloneVo().setDetail("shenzhen");
        System.out.println("v1:"+v1);
        System.out.println("v1==v2:"+(v2==v1));
        System.out.println("v2:"+v2);
    }
}
