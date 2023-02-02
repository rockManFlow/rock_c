package com.kuark.tool.advance.advance20200723;

import java.util.concurrent.ForkJoinTask;

/**
 * @author rock
 * @detail
 * @date 2020/8/24 17:26
 */
public class WorkForkJoinTask extends ForkJoinTask {
    @Override
    public Object getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Object value) {

    }

    @Override
    protected boolean exec() {
        return false;
    }
}
