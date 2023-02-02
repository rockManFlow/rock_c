package com.kuark.tool.model.mybatis.entitys;

import java.io.Serializable;

/**
 * Created by caoqingyuan on 2017/6/30.
 */
public class ExcelEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private TiggerEntity tigger;

    public void setAge(Integer age) {
        this.age = age;
    }

    public TiggerEntity getTigger() {
        return tigger;
    }

    public void setTigger(TiggerEntity tigger) {
        this.tigger = tigger;
    }

    public int getAge() {
        return age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return "id:"+this.id+"||name:"+this.name+"||age:"+this.age;
    }
}
