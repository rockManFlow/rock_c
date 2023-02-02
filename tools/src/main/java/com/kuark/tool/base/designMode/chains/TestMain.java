package com.kuark.tool.base.designMode.chains;

/**
 * @author rock
 * @detail 责任链模式：一个请求会逐步通过各个处理者
 * @date 2020/8/18 11:16
 */
public class TestMain {

    public static void main(String[] args) {
        ChainHandler chainHandler1=new ChainHandler1();
        ChainHandler chainHandler2=new ChainHandler2();
        chainHandler1.setNext(chainHandler2);
        Object result = chainHandler1.exce("22");
        System.out.println("result:"+result);
    }
}
