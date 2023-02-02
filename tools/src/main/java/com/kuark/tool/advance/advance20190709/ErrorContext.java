package com.kuark.tool.advance.advance20190709;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/9 17:00
 */
public class ErrorContext {
    private static final ThreadLocal<ErrorContext> local=new ThreadLocal<>();

    public static ErrorContext instance(){
        ErrorContext errorContext = local.get();
        if(errorContext==null){
            errorContext=new ErrorContext();
            local.set(errorContext);
        }
        return errorContext;
    }
}
