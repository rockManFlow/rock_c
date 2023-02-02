//package com.kuark.tool.model.task.dynamicTask;
//
//import org.quartz.*;
//import org.quartz.impl.JobDetailImpl;
//import org.quartz.impl.StdSchedulerFactory;
//import org.quartz.impl.triggers.CronTriggerImpl;
//import org.springframework.expression.ParseException;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * @author caoqingyuan
// * @detail
// * @date 2018/4/10 15:26
// */
//public class TaskFilter {
//    // 0 0 12 * * ? 每天12点整触发一次
//    // 0 15 10 ? * * 每天10点15分触发一次
//    // 0 15 10 * * ? 每天10点15分触发一次
//    // 0 15 10 * * ? * 每天10点15分触发一次
//    // 0 15 10 * * ? 2005 2005年内每天10点15分触发一次
//    // 0 * 14 * * ? 每天的2点整至2点59分，每分钟触发一次
//    // 0 0/5 14 * * ? 每天的2点整至2点55分，每5分钟触发一次
//    // 0 0/5 14,18 * * ? 每天的2点整至2点55分以及18点整至18点55分，每5分钟触发一次
//    // 0 0-5 14 * * ? 每天的2点整至2点5分，每分钟触发一次
//    // 0 10,44 14 ? 3 WED 每年3月的每个星期三的2点10分以及2点44分触发一次
//    // 0 15 10 ? * MON-FRI 每月周一、周二、周三、周四、周五的10点15分触发一次
//    // 0 15 10 15 * ? 每月15的10点15分触发一次
//    // 0 15 10 L * ? 每月最后一天的10点15分触发一次
//    // 0 15 10 ? * 6L 每月最后一个周五的10点15分触发一次
//    // 0 15 10 ? * 6L 每月最后一个周五的10点15分触发一次
//    // 0 15 10 ? * 6L 2002-2005 2002年至2005年间，每月最后一个周五的10点15分触发一次
//    // 0 15 10 ? * 6#3 每月第三个周五的10点15触发一次
//    // 0 0 12 1/5 * ? 每月1号开始，每5天的12点整触发一次
//    //
//    // 0 11 11 11 11 ? 每年11月11日11点11分触发一次
//    /**
//     * 容器启动时初始化该类时，将任务都加到内存当中
//     *
//     * @throws SchedulerException
//     * @throws ParseException
//     */
//    public void initJobTrigger() throws SchedulerException, ParseException {
//
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
//        // 这里可以读取数据库来设置各个任务
//        List list = new ArrayList();
//        JobPlanDomain t1 = new JobPlanDomain();
//        t1.setCronExpression("* * * * * ?");
//        t1.setValid("0");
//        t1.setJobPlanCode("222222222222");
//        list.add(t1);
//
//        if (null != list && list.size() > 0) {
//            Iterator ite = list.iterator();
//            while (ite.hasNext()) {
//                // 任务对象
//                JobPlanDomain rj = (JobPlanDomain) ite.next();
//                // 任务表达式
//                String cronExpression = rj.getCronExpression();
//                // 新建任务，任务组为默认的Scheduler.DEFAULT_GROUP，需要执行的任务类为TaskReport.class
//                JobDetail jobDetail = new JobDetailImpl();
//                // 新建触发器，触发器为默认的Scheduler.DEFAULT_GROUP
//                CronTrigger cronTrigger = new CronTriggerImpl();
//
//                try {
//                    // 为触发器设置定时表达式
////                    cronTrigger.setCronExpression(cronExpression);
//                    String startTime = "2014-10-17 16:17:10";
//                    String endTime = "2014-10-17 16:17:50";
//                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date startDate = sdf.parse(startTime);
//                    Date endDate = sdf.parse(endTime);
//                    if(startTime.compareTo(endTime) > 0 ) {
//                        throw new Exception("起始时间比结束时间大!");
//                    }
//
////                    cronTrigger.setStartTime(startDate);
////                    cronTrigger.setEndTime(endDate);
//                    // 启动新增定时器任务
//                    scheduler.scheduleJob(jobDetail, cronTrigger);
//
//                } catch (Exception e) {
//                    // 启动验证失败，设置任务标记为禁用
//                    e.printStackTrace();
//                    rj.setValid("1");
//                    // baseDao.updateObject(rj);
//                }
//            }
//        }
//        // 初始化任务只需要执行一次，执行一次后移除初始化触发器
////        scheduler.unscheduleJob("InitTrigger", Scheduler.DEFAULT_GROUP);
//        // 任务启动
//        scheduler.start();
//    }
//
//
//    public static void main(String[] args) {
//        TaskFilter t = new TaskFilter();
//        try {
//            t.initJobTrigger();
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SchedulerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void afterPropertiesSet() throws Exception {
//        try {
//            this.initJobTrigger();
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SchedulerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//}
