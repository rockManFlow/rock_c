package com.kuark.tool.base.schema.assemble.demo;

import com.kuark.tool.base.schema.assemble.AbstractHandler;
import com.kuark.tool.base.schema.assemble.context.HandlerContext;
import org.springframework.stereotype.Component;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/15 10:46
 */
@Component("taskHandler1")
public class TaskHandler1 extends AbstractHandler {
    TaskHandler1(String handlerName) {
        super("TaskHandler1");
    }

    @Override
    public Object handle(HandlerContext context) {
        System.out.println("taskHandler1执行体");
        return null;
    }
}
