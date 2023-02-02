package com.kuark.tool.advance.advance20201105util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by cc on 2016/4/14.
 */
public class MyConnectionUtil {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection conn;
    public MyConnectionUtil(String driver,String url,String username,String password) throws Exception {
        this.driver=driver;
        this.url=url;
        this.username=username;
        this.password=password;
        this.conn=myConnection();
    }
    private Connection myConnection() throws Exception {
        Class.forName(this.driver);
        return DriverManager.getConnection(this.url, this.username, this.password);
    }
    public Connection getConnection(){
        return this.conn;
    }
}
