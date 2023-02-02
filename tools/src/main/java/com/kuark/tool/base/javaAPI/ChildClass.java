package com.kuark.tool.base.javaAPI;

/**
 * @author rock
 * @detail
 * @date 2019/9/29 11:19
 */
public class ChildClass extends SuperClass {

    public static void main(String[] a){
        SuperClass childClass=new ChildClass();
        childClass.write();
        childClass.show1();
    }
}
