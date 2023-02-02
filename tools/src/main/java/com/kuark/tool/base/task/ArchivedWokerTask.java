package com.kuark.tool.base.task;


import com.kuark.tool.advance.advance20201105util.DataBaseUtil;
import com.kuark.tool.advance.advance20201105util.DateUtils;
import com.kuark.tool.base.database.NewMethod;
import com.kuark.tool.base.database.QueryMethod;
import com.kuark.tool.base.database.UpdateMethod;
import com.kuark.tool.base.vo.ArchiveConfigPojo;
import com.kuark.tool.base.vo.ArchivePojo;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caoqingyuan on 2016/4/11.
 */
public class ArchivedWokerTask extends TimerTask {
    private static final Logger logger=Logger.getLogger(ArchivedWokerTask.class);
    private ArchiveConfigPojo obj=null;
    public ArchivedWokerTask(ArchiveConfigPojo obj){
        this.obj=obj;
    }
    public static void schedule(ArchiveConfigPojo obj) {
        Timer timer = new Timer();
        timer.schedule(new ArchivedWokerTask(obj),0);
    }
    /**
     * 执行dayuv2数据库中表的归档
     */
    public void run(){
        logger.info("Archive enter");
        DataBaseUtil dataBase=null;
        try {
            dataBase=new DataBaseUtil(obj.getDb_name());
            fileTask(dataBase,obj);
            dataBase.commitAndClose();
        }catch (SQLException e){
            if(dataBase!=null){
                dataBase.rollbackAndClose();
                logger.info("数据库"+obj.getDb_name()+"中表"+obj.getFrom_table()+"归档失败，已回滚");
            }
            logger.error("error info:",e);
        }
    }
    private static void fileTask(DataBaseUtil dataBase,ArchiveConfigPojo obj) throws SQLException {
        //读取所有符合条件的归档数据的ID和create_time
        List<ArchivePojo> list= QueryMethod.queryTaskOneMonthDatas(obj,dataBase);
        StringBuilder id=new StringBuilder();
        int index=0;
        for(ArchivePojo cronJob:list){
            if(DateUtils.dateOp(30).after(cronJob.getCreate_time())){
                index=index+1;
                if(index==1){
                    id.append("'"+cronJob.getId()+"'");
                }else{
                    id.append(",'"+cronJob.getId()+"'");
                }
            }
        }
        if(id.length()!=0){
            //把符合的插入到t_task_archive表中
            NewMethod.newDataToTaskArchive(id.toString(),dataBase,obj);
            //删除t_task表中的对应数据
            UpdateMethod.deleteVirtTask(id.toString(),dataBase,obj);
            logger.info("数据库"+obj.getDb_name()+"中表"+obj.getFrom_table()+"执行归档，主键分别为："+id.toString());
        }
    }
}
