package com.kuark.tool.advance.advance20190708.base;

/**
 * @author rock
 * @detail 覆盖、重载
 * @date 2023/1/28 18:22
 *
 *
 *
 * 声明为 final 的方法不能被重写。
 *
 * 声明为 static 的方法不能被重写，但是能够被再次声明。
 */
public class NonCover {
    private int age;
    private String name;

    public void show(){
        System.out.println("base show");
    }

    public void show(int age){
        System.out.println("base show"+age);
    }

    public final void show2(){
        System.out.println("base show2");
    }

    public static void show3(){
        System.out.println("base show3");
    }
}
