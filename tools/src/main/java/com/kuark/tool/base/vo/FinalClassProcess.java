package com.kuark.tool.base.vo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rock
 * @detail
 * @date 2019/9/17 10:03
 */
@Slf4j
public class FinalClassProcess {
    private FinalClass finalClass;
    public FinalClassProcess(FinalClass finalClass){
        log.info("input url={},dd={}",finalClass.hashCode(),finalClass);
        this.finalClass=finalClass;
        log.info("input url={},dd={}",this.finalClass.hashCode(),this.finalClass);
    }
}
