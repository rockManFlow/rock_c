package com.kuark.tool.advance.advance20201105util;

import com.kuark.tool.base.vo.ArchiveConfigPojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by cc on 2016/4/13.
 */
public class ArchiveConfigUtil {
    public static List<ArchiveConfigPojo> getArchiveConfigList() throws SQLException {
        Properties props= PropertiesUtil.getFiles();
        //获取归档配置表所在的数据库名
        String dbName=props.getProperty("archive_config_dbname");
        return queryArchiveConfig(dbName);
    }
    /**
     * 查询dayuv2数据库中的对账配置表中的数据
     * @auther 曹庆远
     */
    private static List<ArchiveConfigPojo> queryArchiveConfig(String dbName) throws SQLException {
        String querySql = "select * from `t_archive_config`";
        DataBaseUtil dataBase=new DataBaseUtil(dbName);
        List<HashMap<String, Object>> lst = dataBase.querySql(querySql);
        dataBase.close();
        List<ArchiveConfigPojo> list=new ArrayList<ArchiveConfigPojo>();
        for(HashMap<String, Object> obj:lst){
            ArchiveConfigPojo archiveConfigPojo=new ArchiveConfigPojo();
            archiveConfigPojo.setMajor_key((String)obj.get("major_key"));
            archiveConfigPojo.setCreate_time((String)obj.get("create_time"));
            archiveConfigPojo.setFrom_table((String)obj.get("from_table"));
            archiveConfigPojo.setTo_table((String)obj.get("to_table"));
            archiveConfigPojo.setTime_interval((Integer)obj.get("time_interval"));
            archiveConfigPojo.setQuery_number((Integer)obj.get("query_number"));
            archiveConfigPojo.setDb_name((String)obj.get("db_name"));
            list.add(archiveConfigPojo);
        }
        return list;
    }
}
