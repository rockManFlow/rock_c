package com.kuark.tool.base.designMode.chains;

/**
 * @author rock
 * @detail
 * @date 2020/8/18 11:19
 */
public class ChainHandler2 extends ChainHandler<String, String> {
    @Override
    public String exce(String o) {
        System.out.println("ChainHandler2 input:"+o);
        if(!"22".equals(o)){
            return (String)getNext().exce(o);
        }
        return "ChainHandler2";
    }
}
