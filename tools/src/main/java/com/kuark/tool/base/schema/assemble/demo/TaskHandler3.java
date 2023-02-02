package com.kuark.tool.base.schema.assemble.demo;

import com.kuark.tool.base.schema.assemble.AbstractHandler;
import com.kuark.tool.base.schema.assemble.context.HandlerContext;
import org.springframework.stereotype.Component;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/15 10:46
 */
@Component("taskHandler3")
public class TaskHandler3 extends AbstractHandler {
    TaskHandler3(String handlerName) {
        super("TaskHandler3");
    }

    @Override
    public Object handle(HandlerContext context) {
        System.out.println("taskHandler3执行体");
        return null;
    }
}
