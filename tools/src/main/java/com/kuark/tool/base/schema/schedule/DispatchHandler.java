package com.kuark.tool.base.schema.schedule;

import com.kuark.tool.base.schema.assemble.Handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caoqingyuan
 * @detail 简单任务组装调度器
 * @date 2019/3/18 11:47
 */
public class DispatchHandler {
    private static Map<String,List<Handler>> productHandleMap=new HashMap<>();
    static {
        //初始化任务执行器
    }

    /**
     * 通过product来执行执行的任务
     * @param product
     */
    public static void executeHandler(String product){
        List<Handler> handlerList=productHandleMap.get(product);
        for(Handler handler:handlerList){
            handler.execute(null);
        }
    }
}
