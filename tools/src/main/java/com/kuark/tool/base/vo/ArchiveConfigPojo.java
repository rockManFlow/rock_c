package com.kuark.tool.base.vo;

/**
 * Created by cc on 2016/4/13.
 * 归档配置表实体
 */
public class ArchiveConfigPojo {
    private String major_key;
    private String create_time;
    private String from_table;
    private String to_table;
    private Integer time_interval;
    private Integer query_number;
    private  String db_name;

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getFrom_table() {
        return from_table;
    }

    public void setFrom_table(String from_table) {
        this.from_table = from_table;
    }

    public String getMajor_key() {
        return major_key;
    }

    public void setMajor_key(String major_key) {
        this.major_key = major_key;
    }

    public Integer getQuery_number() {
        return query_number;
    }

    public void setQuery_number(Integer query_number) {
        this.query_number = query_number;
    }

    public Integer getTime_interval() {
        return time_interval;
    }

    public void setTime_interval(Integer time_interval) {
        this.time_interval = time_interval;
    }

    public String getTo_table() {
        return to_table;
    }

    public void setTo_table(String to_table) {
        this.to_table = to_table;
    }
}
