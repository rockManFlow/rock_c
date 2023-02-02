package com.kuark.tool.base.designMode.strategy;

/**
 * @author rock
 * @detail
 * @date 2022/2/21 17:19
 */
public class WorkMain {
    public static void main(String[] args) {
        Work1 w1=new Work1("xxx");
        Work2 w2=new Work2(22);
        WorkAbstract workAbstract = WorkAbstract.workMap.get(w2.getTemplateName());
        workAbstract.execute();
    }
}
