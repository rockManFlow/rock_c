package com.kuark.tool.base.designMode.chains;

/**
 * @author rock
 * @detail
 * @date 2020/8/18 11:18
 */
public abstract class ChainHandler <T,S>{
    private ChainHandler chainHandler;
    public void setNext(ChainHandler chainHandler){this.chainHandler=chainHandler;}
    protected ChainHandler getNext(){return chainHandler;}

    public abstract S exce(T t);
}
