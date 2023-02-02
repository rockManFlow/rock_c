package com.kuark.tool.advance.advance20190708.base;

public class NonExtendThird extends NonExtendTow{
    //先声明，在复制，之后使用
    private static final Integer age;

    static {
        System.out.println("");
        age=10;
    }

    //todo 代码块 final修饰的在使用之前赋值就行，但只能赋值一次
    private final String name;

//    {
//        name="xiaohong";
//    }

    public NonExtendThird(){
        name="dddd";
    }

    public void show(){
        System.out.println("NonExtendThird:"+age);
    }
    public final Integer write(){
        System.out.println("NonExtendThird write");
        return 1;
    }

    public Integer write2(){
        System.out.println("NonExtendTow write2");
        return 0;
    }
}
