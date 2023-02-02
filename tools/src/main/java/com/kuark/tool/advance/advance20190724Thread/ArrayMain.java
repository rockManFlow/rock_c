package com.kuark.tool.advance.advance20190724Thread;

/**
 * @author rock
 * @detail
 * @date 2019/9/16 19:42
 */
public class ArrayMain {
    public static void main(String[] args){
        for(int i=0;i<4;i++){
            new Thread(new ArrayTask("Thread"+i,i+1)).start();
        }
    }
}
