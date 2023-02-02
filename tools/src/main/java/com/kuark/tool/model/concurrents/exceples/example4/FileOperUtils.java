package com.kuark.tool.model.concurrents.exceples.example4;

import com.kuark.tool.model.concurrents.exceples.example4.interfaces.ServiceInter;
import org.junit.Test;

import java.io.*;

/**
 * Created by caoqingyuan on 2017/6/26.
 */
public class FileOperUtils {
    private static final String fileUrl="e:/cccc.txt";
    public static void reader(){
        try {
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileUrl)));
            String st=null;
            StringBuffer buffer=new StringBuffer();
            ServiceInter service=null;
            int num=0;
            while ((st=reader.readLine())!=null){
                ++num;
                buffer.append(st);
                if(num%3000==0){
                    service.service(buffer.toString());
                    buffer.delete(0,buffer.length());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void vv(){
        StringBuffer buffer=new StringBuffer("qwqwqwqwqwqwqwqwqwqwqwqw");
        System.out.println("size="+buffer.length());
        buffer.delete(0,buffer.length());
        System.out.println("last size="+buffer.length());
    }
}
