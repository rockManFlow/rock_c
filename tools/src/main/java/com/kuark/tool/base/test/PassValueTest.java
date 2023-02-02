package com.kuark.tool.base.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2016/5/18.
 * 参数传值--八种基本类型，值。对象类型，地址。
 */
public class PassValueTest {
    public static List createList(){
        List<String> list=new ArrayList<String>();
        list.add("hello");
        list.add("world");
        return list;
    }
    public static void modifyList(List list){
        list.set(0,"ni");
        list.set(1,"hao");
    }
    public static void modifyString(String s){
        s="qw";
    }
    @Test
    public void test1(){
        List<String> list=PassValueTest.createList();
        for(String str:list){
            System.out.println("dd="+str);
        }
        PassValueTest.modifyList(list);
        for(String str:list){
            System.out.println("cc="+str);
        }
//        System.out.println("id="+tv.getId()+"||name="+tv.getName());
        String s=new String("dead");
        PassValueTest.modifyString(s);
        System.out.println("s="+s);
    }
}
