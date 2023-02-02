package com.kuark.tool.base.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoqingyuan on 2016/10/10.
 */
public class FileMoreReadUtilTest {
    @Test
    public void read() {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String fileUrl = "e:/aa.txt";
        try {
            fileInputStream = new FileInputStream(fileUrl);
            List<String> list = new ArrayList<String>();
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String b;
            while ((b = bufferedReader.readLine()) != null) {
                list.add(b);
            }
            for(String st:list){
                StringBuffer contexts = new StringBuffer("INSERT INTO `t_hxb_bankcode` (bank_name,branch_no,bank_code) VALUE(");
//                System.out.println(st.trim());
                String[] split = st.trim().split(" ");
                int i=0;
                while (i<split.length){
                    if(i!=2){
                        contexts.append("'"+split[i]+"',");
                    }else{
                        contexts.append("'"+split[i]+"'");
                    }
//                    System.out.println(split[i]);
                    ++i;
                }
                contexts.append(");");
                System.out.println(contexts.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
