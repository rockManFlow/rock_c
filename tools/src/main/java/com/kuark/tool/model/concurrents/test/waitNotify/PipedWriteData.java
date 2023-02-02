package com.kuark.tool.model.concurrents.test.waitNotify;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class PipedWriteData implements Runnable{
    private PipedOutputStream out;
    public PipedWriteData(PipedOutputStream out){
        this.out=out;
    }
    //管道输出流是管道的发送端
    public void writeData(PipedOutputStream outputStream){
        try{
            System.out.println("write:");
            for(int i=0;i<30;i++){
                String data=""+(i+1);
                outputStream.write(data.getBytes());
                System.out.print(data);
            }
            System.out.println();
            outputStream.close();
        }catch (IOException o){
            o.printStackTrace();
        }
    }

    @Override
    public void run() {
        writeData(out);
    }
}
