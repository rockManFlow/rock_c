package com.kuark.tool.model.test.shiwu;

/**
 * Created by caoqingyuan on 2017/3/17.
 */
public class TestString {
    public static void main(String[] args){
        String a="111";
        String b="222";
        String c="333";
        string(1,2,2);
    }
    //数组的形式，参数不定
    public static void string(int ... st){
        System.out.println("nn="+st[0]);
        System.out.println("nn="+st[1]);
        System.out.println("nn="+st[2]);
        System.out.println("nn="+st[3]);
    }
}
