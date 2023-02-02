package com.kuark.tool.jdk;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/5/19 18:48
 */
public class Person {
    private String id;
    private String name;

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null||obj.getClass()!=getClass()){
            return false;
        }
        if(((Person)obj).getId().equals(getId())){
            return true;
        }
        return false;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
