package com.kuark.tool.base.database;

import com.kuark.tool.advance.advance20201105util.DataBaseUtil;
import com.kuark.tool.base.vo.ArchiveConfigPojo;
import com.kuark.tool.base.vo.ArchivePojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class QueryMethod {
    /**
     * 查询数据库中的表中距当前一个月中的数据
     * @auther 曹庆远
     */
    public static List<ArchivePojo> queryTaskOneMonthDatas(ArchiveConfigPojo archiveConfig, DataBaseUtil dataBase) throws SQLException {
        String querySql = "select "+archiveConfig.getMajor_key()+","+archiveConfig.getCreate_time()+
                " from "+archiveConfig.getFrom_table()+
                " order by "+archiveConfig.getMajor_key()+" asc limit 0,"+archiveConfig.getQuery_number();
        List<HashMap<String, Object>> lst = dataBase.querySql(querySql);
        List<ArchivePojo> list=new ArrayList<ArchivePojo>();
        for(HashMap<String, Object> obj:lst){
            ArchivePojo cronJob=new ArchivePojo();
            cronJob.setId((Long)obj.get(archiveConfig.getMajor_key()));
            cronJob.setCreate_time((Date) obj.get(archiveConfig.getCreate_time()));
            list.add(cronJob);
        }
        return list;
    }

    /**
     * 测试查询数据库中的信息，用于创建xml文件
     * @return
     * @throws SQLException
     */

    public static List<HashMap<String, Object>> queryData() throws SQLException {
        DataBaseUtil dataBase=new DataBaseUtil("payment");
        String querySql = "select * from t_grap";
        List<HashMap<String, Object>> lst = dataBase.querySql(querySql);
        return lst;
    }
}

