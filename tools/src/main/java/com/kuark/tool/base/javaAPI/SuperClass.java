package com.kuark.tool.base.javaAPI;

/**
 * @author rock
 * @detail
 * @date 2019/9/29 11:17
 */
public class SuperClass {
    private String show(){
        return "Super";
    }

    public void write(){
        System.out.println("Super write");
    }

    protected String show1(){
        return "Super protected";
    }
}
