package com.kuark.tool.advance.advance20201111.design.decorator;

/**
 * @author rock
 * @detail 装饰器：对原功能通过装饰器扩展各种新功能
 * 核心：对原有接口的功能进行扩展，原来调用原来接口的地方实现这个新接口
 * 接口名称要与原接口一致
 * @date 2021/8/9 11:06
 */
public class Decorator implements Component {
    private Component component;
    public Decorator(Component component){
        this.component=component;
    }
    @Override
    public void sampleOpreation() {
        component.sampleOpreation();
        //
        System.out.println("扩展功能");
    }
}
