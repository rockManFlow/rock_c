package com.kuark.tool.base.designMode.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rock
 * @detail ²ßÂÔÄ£Ê½
 * @date 2022/2/21 16:51
 */
public abstract class WorkAbstract {
    public static final Map<String,WorkAbstract> workMap=new HashMap<>(8);

    public WorkAbstract(){
        System.out.println("WorkAbstract");
        workMap.put(getTemplateName(),this);
    }

    public abstract String getTemplateName();

    public abstract void execute();
}
