package com.kuark.tool.model.oper;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by caoqingyuan on 2016/10/13.
 */
public class ThreadLocalVar {
    //这是一个线程局部变量
    public static ThreadLocal<Connection> threadSource=new ThreadLocal<Connection>();
    public static Connection getSourceCon(){
        return threadSource.get();
    }
    public static void setSourceCon(Connection connection){
        threadSource.set(connection);
    }
    public static void unSourceCon(){
        Connection connection = threadSource.get();
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
