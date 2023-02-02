package com.kuark.tool.advance.advance20200628;

/**
 * @author rock
 * @detail
 * @date 2021/5/28 14:17
 */
public class LambdaMain {
    @FunctionalInterface
    public interface FunctionLambda{
        String write();
    }
    public String showWrite(FunctionLambda fb){
        System.out.println("start");
        fb.write();
        System.out.println("end");
        return "OK";
    }

    public static void main(String[] args) {
        new LambdaMain().showWrite(()->{
            System.out.println("write");
            return "write ok";
        });
    }
}
