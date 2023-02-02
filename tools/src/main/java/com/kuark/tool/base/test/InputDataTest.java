package com.kuark.tool.base.test;

import org.junit.Test;

import java.io.*;

/**
 * Created by cc on 2016/5/20.
 */
public class InputDataTest {
    public static void main(String[] s) throws IOException {
//        String nonFile="C:\\Users\\cc\\Desktop\\卡表文件\\IN2017052501NONBIN";475---重复462  =13
        String nonFile="C:\\Users\\cc\\Desktop\\卡表文件\\IN2017052501TFRBIN";//6709---重复1925  =4784
        FileInputStream inputStream=new FileInputStream(nonFile);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String sqlPrefix="INSERT INTO `t_card_bin`(`bank_name`,`bank_code`,`card_name`,`card_no_len`,`card_no_mask`,`card_prefix_len`,`card_prefix`,`card_type`,`card_no_min`,`card_no_max`) \n" +
                "VALUES(";
        String st;
        FileOutputStream out=new FileOutputStream("C:\\Users\\cc\\Desktop\\tfrbin.txt");
        while ((st=bufferedReader.readLine())!=null){
            StringBuffer sql=new StringBuffer(sqlPrefix);
            String[] split = st.split(",");
            if(split.length<3){
                continue;
            }
            sql.append("'").append(split[1].trim()).append("',");
            sql.append("'").append(split[0].trim()).append("',");
            sql.append("'").append(split[2].trim()).append("',");
            sql.append("'").append(split[5].trim()).append("',");
            String card_no_mask = links(split[5].trim(), split[6].trim(), 1);
            sql.append("'").append(card_no_mask).append("',");
            sql.append("'").append(split[6].trim().length()).append("',");
            sql.append("'").append(split[6].trim()).append("',");
            String card_type="借记卡";
            if(!split[14].trim().equals("1")){
                card_type="贷记卡";
            }
            sql.append("'").append(card_type).append("',");
            String card_no_min = links(split[5].trim(), split[6].trim(), 3);
            sql.append("'").append(card_no_min).append("',");
            String card_no_max = links(split[5].trim(), split[6].trim(), 2);
            sql.append("'").append(card_no_max).append("'");
            sql.append(");");
            out.write(sql.toString().getBytes());
            out.flush();
        }
        out.close();
    }
    public static String links(String card_no_len,String prefix,Integer mask){//获取card_no_mask:1
        Integer lens=Integer.parseInt(card_no_len);
        Integer prefixLens=prefix.length();
        Integer num=lens-prefixLens;
        StringBuffer sb=new StringBuffer(prefix);
        for(int i=0;i<num;i++){
            if(mask==1) {
                sb.append("x");
            }else if(mask==2){
                sb.append("9");
            }else{
                sb.append("0");
            }
        }
        return sb.toString();
    }
    @Test
    public void ttt(){
        String links = links("16", "458441", 1);
        System.out.println(links);
    }
}
