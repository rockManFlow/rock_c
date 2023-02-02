package com.kuark.tool.model.springs.datasource;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/3/13 15:23
 */
public class DataSourceService {

    @DataSource(name = "dataSourceKeyR")
    public void read(String sql){
        System.out.println("执行读sql");
    }

    @DataSource(name = "dataSourceKeyRW")
    public void write(String sql){
        System.out.println("执行写sql");
    }
}
