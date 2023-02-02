package com.kuark.tool.advance.advance20190815.Bus;

/**
 * @author rock
 * @detail
 * @date 2019/8/16 17:43
 */
public interface Subscriber {
    public void onNext(Event object);

    public void onError(Throwable t);

    public void onComplete();
}
