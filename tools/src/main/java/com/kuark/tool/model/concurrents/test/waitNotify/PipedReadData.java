package com.kuark.tool.model.concurrents.test.waitNotify;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by caoqingyuan on 2017/7/25.
 */
public class PipedReadData implements Runnable{
    private PipedInputStream read;
    public PipedReadData(PipedInputStream read){
        this.read=read;
    }
    public void readData(PipedInputStream read){
        try{
            System.out.println("read start");
            String rr=new String(IOUtils.toByteArray(read));
            System.out.println("read:"+rr);
            read.close();
        }catch (IOException o){
            o.printStackTrace();
        }
    }

    @Override
    public void run() {
        readData(read);
    }
}
