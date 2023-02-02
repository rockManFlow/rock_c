package com.kuark.tool.advance.advance20201111.chain;

public class ChainMain {
    public static void main(String[] args) {
        SoltChain chain=new SoltChain();

        for(int i=0;i<10;i++){
            SoltInfo info=new SoltInfo();
            info.setNo(i);
            chain.addSolt(info);
        }

//        SoltInfo soltInfo = chain.getFirst();
//        while (soltInfo!=null){
//            System.out.println("out no:"+soltInfo.getNo());
//            soltInfo=soltInfo.getNext();
//        }

        /**
         * 这种方式的调用链更好一些
         */
        SoltInfo entry = chain.getFirst();
        if(entry!=null){
            entry.entry();
        }
    }
}
