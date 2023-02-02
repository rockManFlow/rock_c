package com.kuark.tool.advance.advance20200313;

/**
 * @author rock
 * @detail 内部类的使用
 * @date 2020/3/30 14:15
 */
public class MyClassInner {
    private String name;
    private Integer age;

    //内部类可以访问外部类的属性
    public class innerClass{
        private String desc;

        public void show(){
            System.out.println(name+"|"+age+"|"+desc);
        }
        //内部类中不能定义static方法
//        public static void show2(){
//
//        }
    }

    //静态内部类是比较特殊的存在，--这个类就会升级为顶级类，与普通类使用没区别
    public static class InnerClassB{
        //可以定义static的方法
        public static void pint(){

        }
    }

    public static void main(String[] args) {
        //创建内部类对象，必须先创建外部类的对象
        MyClassInner myClassInner=new MyClassInner();
        myClassInner.new innerClass();

        //静态类就可以这样来实现，而普通内部类的却不行
        MyClassInner.InnerClassB b=new MyClassInner.InnerClassB();
    }
}
