package com.kuark.tool.base.task;


import com.kuark.tool.advance.advance20201105util.ArchiveConfigUtil;
import com.kuark.tool.base.vo.ArchiveConfigPojo;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by caoqingyaun on 2016/4/11.
 */
public class ArchivedTask extends TimerTask {
        private static final Logger logger=Logger.getLogger(ArchivedTask.class);
        private static int counter = 0;
        public static void schedule() {
            Timer timer = new Timer();
            timer.schedule(new ArchivedTask(),0,1000L);
        }
    /**
     * 执行dayuv2数据库中表的归档
     */
    public void run(){
        fileTask();
    }

    private void fileTask(){
        List<ArchiveConfigPojo> archiveList=getArchiveConfigs();
        counter++;
        for (ArchiveConfigPojo obj:archiveList){
            if(counter%obj.getTime_interval()==0){
                ArchivedWokerTask.schedule(obj);
            }
        }
    }
    //查询归档配置表文件
    private static List<ArchiveConfigPojo> getArchiveConfigs(){
        List<ArchiveConfigPojo> archiveConfigList= null;
        try {
            archiveConfigList= ArchiveConfigUtil.getArchiveConfigList();
        } catch (SQLException e) {
            logger.error("查询归档配置表文件出现异常");
        }
        return archiveConfigList;
    }
}
