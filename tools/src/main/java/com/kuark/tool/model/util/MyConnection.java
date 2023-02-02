package com.kuark.tool.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by caoqingyuan on 2016/6/20.
 */
public class MyConnection {
    private Connection conn;
    public MyConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/share?useUnicode=true&characterEncoding=UTF-8", "root", "123");
            conn.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
    public Connection getConn(){
        return conn;
    }
}
