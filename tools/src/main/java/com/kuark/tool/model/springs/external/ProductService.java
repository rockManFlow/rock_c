package com.kuark.tool.model.springs.external;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by caoqingyuan on 2017/7/4.
 */
public class ProductService {
    public void run(){
        System.out.println("this is a productService method");
        System.out.println(".............");
        System.out.println("ProductService end");
    }
    public void before(){
        System.out.println("MerterService before");
    }
    public void after(){
        System.out.println("MerterService after");
    }
    public void afterThrowing(){
        System.out.println("MerterService 异常通知");
    }
    public void afterReturn(){
        System.out.println("MerterService 最终通知");
    }
    public void around(ProceedingJoinPoint point){
        System.out.println("MerterService around");
        Object[] args = point.getArgs();
        System.out.println("request param:"+args.length);
        Object result="init";
        try {
            result=point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("result:"+(String)result);
        System.out.println("MerterService around end");
    }
}
