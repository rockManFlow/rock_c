package com.kuark.tool.model.dao;


import com.kuark.tool.model.oper.AppContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by caoqingyuan on 2016/10/28.
 */
public class QueryOper {
    public static void queryPayment(String id){//340
        //spring的bean的获取有单利和非单利之分，获取bean默认单利，所以获取连接是从一个bean
        DataSource dataSource = AppContext.createDataSource();
        try {
            String sql="select * from t_task where task_id='"+id+"'";
            Connection conn=dataSource.getConnection();
            PreparedStatement pre12 = conn.prepareStatement(sql);
            ResultSet resultSet = pre12.executeQuery();
            while (resultSet.next()){
                int i=1;
                while (true) {
                    try {
                        System.out.println("" + resultSet.getString(i));
                        ++i;
                    }catch (SQLException e){
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
