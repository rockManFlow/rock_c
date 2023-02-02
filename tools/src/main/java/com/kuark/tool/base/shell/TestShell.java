package com.kuark.tool.base.shell;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by caoqingyuan on 2017/11/14.
 */
public class TestShell {
    public static void mian(String[] args) throws IOException, InterruptedException {
        Process process=null;
        String command="echo 'process info'";
        process = Runtime.getRuntime().exec(command);
        int status=process.waitFor();
        if(status==0){
            //
            InputStream inputStream = process.getInputStream();
            byte[] b=new byte[1024];
            new DataInputStream(inputStream).read(b);

            System.out.println("by:"+new String(b));
        }
    }
}
