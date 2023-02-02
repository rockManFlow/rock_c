package com.kuark.tool.model.test;

/**
 * Created by caoqingyuan on 2017/7/4.
 */
public class TestPojo {
    private int id;
    private String name;
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "id:"+this.id+"|name:"+this.name+"|addr:"+this.addr;
    }
}
