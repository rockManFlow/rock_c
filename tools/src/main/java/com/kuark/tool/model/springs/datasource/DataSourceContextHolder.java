package com.kuark.tool.model.springs.datasource;

/**
 * @author caoqingyuan
 * @detail 设置对应的数据源名称
 * @date 2019/3/13 14:45
 */
public class DataSourceContextHolder {
    private final static ThreadLocal<String> contextHolder=new ThreadLocal<>();

    /**
     * @Description: 设置数据源类型
     * @param dataSourceType  数据库类型
     * @return void
     * @throws
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     * @param
     * @return String
     * @throws
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     * @param
     * @return void
     * @throws
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
