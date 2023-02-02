package com.kuark.tool.base.vo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail 常量类与普通的类一样
 * @date 2019/9/17 10:02
 */
@Slf4j
public class FinalClassMain {
    public static void main(String[] args){
        FinalClass finalClass=new FinalClass();
        log.info("main url={},dd={}",finalClass.hashCode(),finalClass);
        new FinalClassProcess(finalClass);
    }
}
