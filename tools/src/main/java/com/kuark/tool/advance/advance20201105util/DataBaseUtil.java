package com.kuark.tool.advance.advance20201105util;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by cc on 2016/4/14.
 */
public class DataBaseUtil {
    private static final Logger logger= Logger.getLogger(DataBaseUtil.class);
    private Connection conn;
    public DataBaseUtil(String dbName) throws SQLException {
        conn=setConnection(dbName);
        conn.setAutoCommit(false);
    }
    public static Connection setConnection(String dbName){
        Connection myConnection=null;
        try{
            Properties props= PropertiesUtil.getFiles();
            String driver=props.getProperty(dbName+".driver");
            String url=props.getProperty(dbName+".url");
            String username=props.getProperty(dbName+".username");
            String password=props.getProperty(dbName+".password");
            myConnection=new MyConnectionUtil(driver,url,username,password).getConnection();
        }catch(Exception e){
            logger.error("获取数据库连接异常",e);
        }
        return myConnection;
    }
    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    public void rollbackAndClose(){
        try{
            conn.rollback();
        }catch(SQLException e){
            logger.error(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    public void commitAndClose(){
        try{
            conn.commit();
        }catch(SQLException e){
            logger.error(e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
    public List<HashMap<String, Object>> querySql(String sql) throws SQLException {
        if(sql==null){
            return null;
        }
        Statement sta = conn.createStatement();
        return getResults(sta.executeQuery(sql));
    }
    public int updateSql(String sql) throws SQLException {
        if(sql==null){
            return -1;
        }
        Statement sta=conn.createStatement();
        int ret=sta.executeUpdate(sql);
        return ret;
    }
    //处理查询数据库中返回结果
    public List<HashMap<String, Object>> getResults(ResultSet result) throws SQLException {
        List list=new ArrayList();
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while(result.next()) {
            HashMap hashmap = new HashMap(columnCount);

            for (int i = 1; i <= columnCount; ++i) {
                Object obj = result.getObject(i);
                if (obj != null && obj instanceof BigDecimal) {
                    BigDecimal bigdecimal = (BigDecimal) obj;
                    if (resultSetMetaData.getScale(i) == 0) {
                        if (bigdecimal.toString().length() < 3) {
                            obj = Byte.valueOf(bigdecimal.byteValue());
                        } else if (bigdecimal.toString().length() < 5) {
                            obj = Short.valueOf(bigdecimal.shortValue());
                        } else if (bigdecimal.toString().length() < 10) {
                            obj = Integer.valueOf(bigdecimal.intValue());
                        } else if (bigdecimal.toString().length() < 19) {
                            obj = Long.valueOf(bigdecimal.longValue());
                        }
                    }
                }

                hashmap.put(resultSetMetaData.getColumnLabel(i).toLowerCase(), obj);
            }
            list.add(hashmap);
        }
        return list;
    }
}
