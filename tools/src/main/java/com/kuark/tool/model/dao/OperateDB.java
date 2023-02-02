package com.kuark.tool.model.dao;

import com.kuark.tool.model.util.MyConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据库连接池的使用
 * Created by cc on 2016/6/7.
 */
//对应dao层的bean
@Service
public class OperateDB {
    //通过类型注入
    //@Autowired
    //通过name来进行赋值
    //@Resource
    //这种是通过注解的形式(依赖注入)直接获取数据库连接池对象
    @Autowired
//    private DataSource dataSource;

    public void addData() throws SQLException {
//        Connection conn = dataSource.getConnection();
//        System.out.println("conn" + conn);
    }

    //批量插入数据
    public void insertDTDb() throws SQLException {
        System.out.println("start");
//        Connection conn = dataSource.getConnection();
//        System.out.println("conn"+conn);
    }

    public void insertDataTDB() throws SQLException, InterruptedException {
//        Connection conn=dataSource.getConnection();
        Connection conn = new MyConnection().getConn();
        conn.setAutoCommit(false);
//        PreparedStatement pre13=conn.prepareStatement("LOCK TABLES t_grap WRITE");
//        pre13.execute();
        PreparedStatement pre12 = conn.prepareStatement("SELECT count(id) FROM t_grap");
        PreparedStatement pre = conn.prepareStatement("INSERT INTO t_grap(grap,classes) values(?,?)");
        int i = 0;
        while (i < 10) {
            pre.setString(1, "呵呵");
            pre.setString(2, "va");
            pre.executeUpdate();
            ++i;
            System.out.println("i=" + i);
        }
//        pre.executeUpdate();
        conn.commit();
        if (pre != null) {
            pre.close();
        }
        if (pre12 != null) {
            pre12.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void main(String[] s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OperateDB nn = new OperateDB();
                Connection conn = new MyConnection().getConn();
                try {
                    nn.insertOneData(conn);
                    conn.commit();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                OperateDB nn = new OperateDB();
                Connection conn = new MyConnection().getConn();
                try {
                    nn.insertOneData(conn);
                    conn.commit();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void insertOneData(Connection conn) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO s_center(`c_content`,`c_tag`,`c_createT`,`c_updateT`,`c_num`,`c_status`) VALUES ('测试呵呵','0',now(),now(),0,'解决')";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("One finish");
        } catch (SQLException s) {
            throw s;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
