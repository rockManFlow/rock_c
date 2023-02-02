package com.kuark.tool.base.database;

import com.kuark.tool.advance.advance20201105util.DataBaseUtil;
import com.kuark.tool.base.vo.ArchiveConfigPojo;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class NewMethod {

    private static final Logger logger = Logger.getLogger(NewMethod.class);

    /**
     * 向新表中插入数据
     * 曹庆远
     */
    public static int newDataToTaskArchive(String idStr, DataBaseUtil dataBaseStore, ArchiveConfigPojo archiveConfig) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("\n" +
                "insert into\n" +
                "    "+archiveConfig.getTo_table()+" " +
                "select * from "+archiveConfig.getFrom_table()+" where "+archiveConfig.getMajor_key()+" in( " + idStr+" )");
        return newDatas(dataBaseStore,sb,archiveConfig.getDb_name());
    }
    //执行插入语句
    public static int newDatas(DataBaseUtil dataBase,StringBuilder sb,String dbName) throws SQLException {
        if(dataBase==null){
            dataBase = new DataBaseUtil(dbName);
        }
        int ret=-1;
        try {
            ret=dataBase.updateSql(sb.toString());
        } catch (SQLException e) {
            if (dataBase != null) {
                dataBase.rollbackAndClose();
            }
            throw e;
        }
        return ret;
    }
}
