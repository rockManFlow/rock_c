package com.kuark.tool.base.designMode.strategy;

/**
 * @author rock
 * @detail
 * @date 2022/2/21 16:59
 */
public class Work2 extends WorkAbstract {
    private Integer input;

    public Work2(Integer input){
        this.input=input;
        System.out.println("work2");
    }
    @Override
    public String getTemplateName() {
        return "WORK2";
    }

    @Override
    public void execute() {
        System.out.println("execute2 "+getTemplateName()+" end "+this.input);
    }
}
