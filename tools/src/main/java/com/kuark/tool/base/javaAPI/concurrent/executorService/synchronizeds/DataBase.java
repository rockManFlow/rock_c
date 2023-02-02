package com.kuark.tool.base.javaAPI.concurrent.executorService.synchronizeds;

/**
 * Created by caoqingyuan on 2016/11/2.
 */
public class DataBase {
    public static int num=0;
    private String name;
    private int age;
    public void statisticsNum(){
        ++num;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }
}
