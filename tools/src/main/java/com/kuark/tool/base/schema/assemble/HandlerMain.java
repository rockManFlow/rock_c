package com.kuark.tool.base.schema.assemble;

import com.kuark.tool.base.schema.HandleApplicationContext;
import com.kuark.tool.base.schema.assemble.context.HandlerContext;
import com.kuark.tool.base.schema.schedule.DispatchHandler;

import java.util.*;

/**
 * @author caoqingyuan
 * @detail 可插拔式的顺序调用
 * @date 2019/3/15 11:21
 */
public class HandlerMain {
    public static void main(String[] args){
        DispatchHandler.executeHandler("");
        System.out.println("main end");

    }
}
