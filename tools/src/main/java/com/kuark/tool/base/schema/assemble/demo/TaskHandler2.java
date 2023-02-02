package com.kuark.tool.base.schema.assemble.demo;

import com.kuark.tool.base.schema.assemble.AbstractHandler;
import com.kuark.tool.base.schema.assemble.context.HandlerContext;
import org.springframework.stereotype.Component;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/15 10:46
 */
@Component("taskHandler2")
public class TaskHandler2 extends AbstractHandler {
    TaskHandler2(String handlerName) {
        super("TaskHandler2");
    }

    @Override
    public Object handle(HandlerContext context) {
        System.out.println("taskHandler2执行体");
        return null;
    }
}
