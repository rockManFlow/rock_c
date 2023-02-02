package com.kuark.tool.base.enums;

/**
 * TODO 枚举类是一个常量类final，不可被继承
 * 枚举就是一个常量集
 * Created by caoqingyuan on 2017/1/13.
 */
public enum CheckCode {
    //实际上就是一个public static final常量
    RED("RED1","红红"),BLUE("BLUE1","解决"),BLOCK("BLOCK1","嘿嘿");
    //作用是由这个构造器进行给枚举常量进行初始化赋值，并可取到对应的常量值
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private CheckCode(String code,String value){
        this.code=code;
        this.value=value;
    }
}
