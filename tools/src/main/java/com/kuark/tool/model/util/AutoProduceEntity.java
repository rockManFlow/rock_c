package com.kuark.tool.model.util;


import java.io.IOException;

/**
 * Created by caoqingyuan on 2016/10/28.
 */
public class AutoProduceEntity {
    public static void main(String[] args) throws IllegalAccessException {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String pwd = "123";
        String tableName = "t_main";
//        PojoGenerator pojoGenerator = new PojoGenerator();
//        try {
//            pojoGenerator.generateFile(url,user,pwd,tableName,"D:\\modAndLi\\src\\main\\java\\com\\model\\test\\shiwu","com.model.test.shiwu");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("done");
    }
}
