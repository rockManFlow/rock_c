package com.kuark.tool.advance.advance20201111;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author rock
 * @detail
 * @date 2021/2/20 11:08
 */
public class SeriableVo implements Serializable {
    private String name;
    private Integer age;

    public String getName() {
        System.out.println("这是一个病毒getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("这是一个病毒setName");
        this.name = name;
    }

    public Integer getAge() {
        System.out.println("这是一个病毒getAge");
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("这是一个病毒setAge");
        this.age = age;
    }

    public SeriableVo(){
        System.out.println("这是一个病毒SeriableVo");
    }


}
