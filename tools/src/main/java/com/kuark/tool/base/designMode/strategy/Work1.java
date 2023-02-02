package com.kuark.tool.base.designMode.strategy;

/**
 * @author rock
 * @detail
 * @date 2022/2/21 16:58
 */
public class Work1 extends WorkAbstract {
    private String input;

    public Work1(String input){
        this.input=input;
        System.out.println("work1");
    }
    @Override
    public String getTemplateName() {
        return "WORK1";
    }

    @Override
    public void execute() {
        System.out.println("execute1 "+getTemplateName()+" end "+this.input);
    }
}
