package com.kuark.tool.model.task;

import com.kuark.tool.model.dao.OperateDB;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by cc on 2016/6/8.
 */
@Component
public class OutTask {
//    private OperateDB operateDB;
    public  static Logger logger= Logger.getLogger(OutTask.class);
//    @Scheduled(cron = "0/5 * * * * ?")
    public void run(){
        logger.info("ree 手动");
//        try {
////            operateDB.insertDTDb();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        logger.info("run finish");
//        try {
//            int i = 1;
//            while (i < 100) {
//                logger.info("run start");
//                logger.info("run end");
//                ++i;
//            }
//        }catch (Exception e){
//            try {
//                logger.info("暂停",e);
//                Thread.sleep(4*60*1000);
////                run();
//            } catch (InterruptedException e1) {
//                logger.error("线程sleep异常");
//            }
//        }
    }

    @Test
    public void test(){
//        System.out.println("start="+operateDB);
        try {
            OperateDB operateDB=new OperateDB();
            operateDB.insertDTDb();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
