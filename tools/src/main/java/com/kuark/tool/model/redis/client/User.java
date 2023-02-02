package com.kuark.tool.model.redis.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoqingyuan on 2017/7/6.
 */
public class User {
    private int id;
    private String name;
    private Integer age;
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString(){
        return "id:"+id+"|name:"+name+"|age:"+age+"|addr:"+addr;
    }
    public static void main(String[] args){
        List list=new ArrayList();
        list.add(null);
        list.add(null);
        System.out.println("size:"+list.size());
    }
}
