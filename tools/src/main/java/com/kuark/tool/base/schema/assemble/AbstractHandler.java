package com.kuark.tool.base.schema.assemble;

import com.kuark.tool.base.schema.assemble.context.HandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/15 11:02
 */
@Slf4j
public abstract class AbstractHandler implements Handler{
    private String handlerName;
    public AbstractHandler(String handlerName){
        this.handlerName=handlerName;
    }
    public AbstractHandler(){
        this("");
    }

    @Override
    public void execute(HandlerContext context){

        log.info("开始执行执行器【"+handlerName+"】");
        if(!context.getBeforeStatus()){
            log.info("组装任务调用中断，执行器【"+handlerName+"】未执行,任务taskId【"+context.getTaskId()+"】");
        }

        handle(context);
        log.info("【"+handlerName+"】任务执行体");

        context.setBeforeStatus(true);
        log.info("执行器【"+handlerName+"】执行结束");
    }
}
