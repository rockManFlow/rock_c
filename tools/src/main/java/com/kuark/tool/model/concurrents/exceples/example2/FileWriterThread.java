package com.kuark.tool.model.concurrents.exceples.example2;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by caoqingyuan on 2017/6/22.
 */
public class FileWriterThread implements Runnable {
    public static final String url="e:/cccc.txt";
    @Override
    public void run() {
        System.out.println("start write data");
        FileOutputStream out =null;
        try {
            out = new FileOutputStream(url,true);
            int isMark=0;
            while (true) {
                String poll = (String) Contants.queue.poll();
                if(poll!=null) {
                    isMark=0;
                    out.write((poll + "\r\n").getBytes());
                    out.flush();
                }else {
                    System.out.println("not data sleep 100ms");
                    Thread.sleep(100);
                    ++isMark;
                    if(isMark>20){
                        System.out.println("not new data need service");
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
