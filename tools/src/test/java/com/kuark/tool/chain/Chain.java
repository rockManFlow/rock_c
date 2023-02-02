package com.kuark.tool.chain;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/5/7 20:51
 */
public abstract class Chain {
    private Chain chain;

    abstract void handler();

    void setChain(Chain chain){
        this.chain=chain;
    }

    public void execute(){
        System.out.println("start");
        handler();
        if(chain!=null){
            chain.execute();
        }
        return;
    }

    public static void main(String[] args){
        ChainA a=new ChainA();
        ChainB b=new ChainB();
        a.setChain(b);
        a.execute();
        System.out.println("end");
    }
}
