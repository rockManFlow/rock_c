package com.kuark.tool.base.schema.assemble.context;

import lombok.Getter;
import lombok.Setter;

/**
 * @author caoqingyuan
 * @detail 执行器上下文
 * @date 2019/3/15 10:47
 */
@Setter
@Getter
public class HandlerContext {
    /**
     * 之前任务执行出现问题或者不想让之后的任务执行，需要把这个状态至为false
     */
    private Boolean beforeStatus=Boolean.FALSE;
    private Integer TaskId;
}
