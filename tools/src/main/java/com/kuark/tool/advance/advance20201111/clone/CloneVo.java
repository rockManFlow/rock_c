package com.kuark.tool.advance.advance20201111.clone;

import lombok.Data;

/**
 * @author rock
 * @detail
 * @date 2021/6/2 11:26
 */
public class CloneVo implements Cloneable{
    private String name;
    private Integer age;
    private String addr;
    private ParamCloneVo paramCloneVo;

    @Override
    public String toString() {
        return "CloneVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", paramCloneVo=" + paramCloneVo +
                '}';
    }

    @Override
    public CloneVo clone(){
        CloneVo c=null;
        try {
            //浅克隆
            //调用父类object的native方法进行复制，属于浅复制
            c=(CloneVo)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //深度克隆
        c.paramCloneVo=paramCloneVo.clone();
        return c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public ParamCloneVo getParamCloneVo() {
        return paramCloneVo;
    }

    public void setParamCloneVo(ParamCloneVo paramCloneVo) {
        this.paramCloneVo = paramCloneVo;
    }
}
