package com.kuark.tool.base.javaAPI.base.generic;

/**
 * 在类中引入泛型的类型变量
 * Created by caoqingyuan on 2018/1/17.
 */
public class MyGen<O,P> {
    private O name;
    private O detail;
    private P age;

    public O getDetail() {
        return detail;
    }

    public void setDetail(O detail) {
        this.detail = detail;
    }

    public P getAge() {
        return age;
    }

    public void setAge(P age) {
        this.age = age;
    }

    public O getName() {
        return name;
    }

    public void setName(O name) {
        this.name = name;
    }

    MyGen(O name, P age){
        this.name=name;
        this.age=age;
    }

    public static void main(String[] args){
        MyGen gen=new MyGen<String,Integer>("dddd",11);
        System.out.println(gen.getName()+"|"+gen.getAge());
    }
}
