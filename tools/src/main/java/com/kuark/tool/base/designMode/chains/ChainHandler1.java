package com.kuark.tool.base.designMode.chains;

/**
 * @author rock
 * @detail
 * @date 2020/8/18 11:19
 */
public class ChainHandler1 extends ChainHandler<String, String> {
    @Override
    public String exce(String o) {
        System.out.println("ChainHandler1 input:"+o);
        if(!"11".equals(o)){
            return (String)getNext().exce(o);
        }
        return "ChainHandler1";
    }
}
