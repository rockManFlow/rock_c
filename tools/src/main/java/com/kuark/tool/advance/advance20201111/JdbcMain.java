package com.kuark.tool.advance.advance20201111;

import java.sql.*;

/**
 * @author rock
 * @detail
 * @date 2021/6/16 18:07
 */
public class JdbcMain {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.kuark.tool.advance.advance20201111.Apple");
//        Object o = aClass.newInstance();

    }
    public static void main1(String[] args) {
        // 数据库驱动类名的字符串
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接串
        String url = "jdbc:mysql://127.0.0.1:3306/jdbctest";
        // 用户名
        String username = "root";
        // 密码
        String password = "mysqladmin";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、加载数据库驱动（ 成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName("com.mysql.jdbc.Driver" );
            // 2、获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3、获取数据库操作对象
            stmt = conn.createStatement();//静态SQL执行对象
            /**
             * 预操作对象
             * PreparedStatement pstmt = conn.prepareStatement("select * from user1 where id = ?") ;//动态SQL执行对象
             *             pstmt.setString(1,"sss");
             *             pstmt.executeQuery();
             */
            // 4、定义操作的SQL语句
            String sql = "select * from user1 where id = 100";
            // 5、执行数据库操作
            rs = stmt.executeQuery(sql);
            // 6、获取并操作结果集
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7、关闭对象，回收数据库资源
            if (rs != null) { //关闭结果集对象
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // 关闭数据库操作对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
