package com.kuark.tool.advance.advance20190709;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/9 17:08
 */
public class ErrorContextTest {
    public static void main(String[] args){
        for(int i=0;i<3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ErrorContext.instance());
                }
            }).start();
        }
        System.out.println("end");
    }
}
