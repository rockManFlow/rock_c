//package com.kuark.tool.model.test.shiwu;
//
//import aming.framework.db.MysqlConnection;
//
///**
// * Created by caoqingyuan on 2016/11/24.
// */
//public class AppUtil {
//    public static MysqlConnection createSelfMysqlConn() {
//        try {
//            MysqlConnection mysqlConnection = new MysqlConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "123");
//            mysqlConnection.getM_Connection().setAutoCommit(false);
//            return mysqlConnection;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
