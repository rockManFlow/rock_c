package com.kuark.tool.advance.advance20190909;

/**
 * @author rock
 * @detail
 * @date 2019/9/9 10:20
 */
public class TestMain {

    public static void main(String[] args){
        ThreadLocal local=new ThreadLocal();
        local.set("aaa");
        Object o = local.get();
    }
}
