package com.kuark.tool.base.schema.assemble;

import com.kuark.tool.base.schema.assemble.context.HandlerContext;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/15 10:44
 */
public interface Handler {
    /**
     * 具体任务实现
     * @param context
     * @return
     */
    Object handle(HandlerContext context);

    void execute(HandlerContext context);
}
