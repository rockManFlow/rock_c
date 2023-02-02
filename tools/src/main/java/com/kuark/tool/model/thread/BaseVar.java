package com.kuark.tool.model.thread;

import java.sql.SQLException;

/**
 * Created by caoqingyuan on 2016/12/6.
 */
public class BaseVar {
    private static ThreadLocal<BasePojo> dbConnections = new ThreadLocal<BasePojo>();
    public static Integer aa;
    public static void holdDbConn(BasePojo basePojo,Integer gg) throws SQLException {
        if (dbConnections.get() == null) {
            dbConnections.set(basePojo);
        }
        aa=gg;
        System.out.println("holdDbConn="+dbConnections.get().getAge()+"aa="+aa);;
    }
    public static void unHoldDbConn() {
        dbConnections.set(null);
    }
}
