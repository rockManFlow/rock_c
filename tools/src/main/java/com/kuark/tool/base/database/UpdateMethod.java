package com.kuark.tool.base.database;

import com.kuark.tool.advance.advance20201105util.DataBaseUtil;
import com.kuark.tool.base.vo.ArchiveConfigPojo;

import java.sql.SQLException;

public class UpdateMethod {
    /**
     * 删除表中的对应数据
     * 曹庆远
     */
    public static int deleteVirtTask(String idStr, DataBaseUtil dataBase, ArchiveConfigPojo archiveConfig) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from  "+archiveConfig.getFrom_table()+" where "+archiveConfig.getMajor_key()+" in("+idStr+" )");
        return NewMethod.newDatas(dataBase,sb,archiveConfig.getDb_name());
    }
}
