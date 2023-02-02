package com.kuark.tool.advance.advance20190815.google;

/**
 * @author rock
 * @detail
 * @date 2019/9/3 10:37
 */
public class LocalEvent implements BaseEvent<LogMsg>{
    private LogMsg t;

    @Override
    public void setContext(LogMsg o) {
        this.t=o;
    }

    @Override
    public LogMsg getContext(){
        return this.t;
    }
}
