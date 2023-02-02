package com.kuark.tool.model.mybatis.entitys;

/**
 * Created by caoqingyuan on 2017/7/12.
 */
public class SpringEntity {
    private String name;
    private Integer age;

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

    public String toString(){
        return "name:"+name+",age:"+age;
    }
}
