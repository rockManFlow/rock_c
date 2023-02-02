package com.kuark.tool.base.designMode.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/1 14:18
 */
public class StrategyExample {
    public static final Map<String,Class> handerMap=new ConcurrentHashMap<>();

    public static void init(){
        handerMap.put("",ClassImpl.class);
    }

    public static AbstractClass getHander(String contextType) throws IllegalAccessException, InstantiationException {
        return (AbstractClass)handerMap.get(contextType).newInstance();
    }
}
