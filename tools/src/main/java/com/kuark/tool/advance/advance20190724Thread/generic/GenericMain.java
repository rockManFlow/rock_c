package com.kuark.tool.advance.advance20190724Thread.generic;

/**
 * @author rock
 * @detail
 * @date 2022/2/11 10:52
 */
public class GenericMain {
    public static void main(String[] args) {
        //接口、类泛型
    }

    //泛型接口当做参数 通配符
    public static void setParam(Genericor<?> genericor){

    }
    //泛型方法
    public <T> String getName(T a){
        return null;
    }

    public <T extends Object> void setName(T a){}
}
