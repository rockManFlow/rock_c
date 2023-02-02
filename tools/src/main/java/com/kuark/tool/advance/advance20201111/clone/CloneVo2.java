package com.kuark.tool.advance.advance20201111.clone;

import java.io.*;

/**
 * @author rock
 * @detail
 * @date 2021/6/2 11:26
 */
public class CloneVo2 implements Serializable {
    private String name;
    private Integer age;
    private String addr;
    private ParamCloneVo2 paramCloneVo;

    public CloneVo2 deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);
        //把当前对象写到输出流中，原来的对象还是存储在JVM中
        objectOutputStream.writeObject(this);

        ByteArrayInputStream inputStream=new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
        //从输出流中读取出对象
        return (CloneVo2)objectInputStream.readObject();
    }

    public ParamCloneVo2 getParamCloneVo() {
        return paramCloneVo;
    }

    public void setParamCloneVo(ParamCloneVo2 paramCloneVo) {
        this.paramCloneVo = paramCloneVo;
    }

    @Override
    public String toString() {
        return "CloneVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", ParamCloneVo2=" + paramCloneVo +
                '}';
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
}
