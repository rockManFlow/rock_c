package com.kuark.tool.advance.advance20201111.chain;

/**
 * 自定义实现对象chain链表--链表实现
 */
public class SoltChain {
    private SoltInfo first;
    private SoltInfo end;

    public void addSolt(SoltInfo soltInfo){
        if(first==null){
            first=soltInfo;
            end=first;
            return;
        }
        end.setNext(soltInfo);
        end=soltInfo;
    }

    public SoltInfo getFirst(){
        return first;
    }

    public SoltInfo getEnd(){
        return end;
    }
}
