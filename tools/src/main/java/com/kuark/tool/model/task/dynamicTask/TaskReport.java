//package com.kuark.tool.model.task.dynamicTask;
//
//import org.quartz.*;
//
//import java.util.Iterator;
//import java.util.Set;
//
///**
// * @author caoqingyuan
// * @detail 任务实现类
// * @date 2018/4/10 15:30
// */
//public class TaskReport implements Job{
//    /**
//     * 报表生成任务
//     */
//    public void execute(JobExecutionContext je) throws JobExecutionException {
//        System.out.println(je.getJobDetail().getKey());
//        System.out.println(je.getJobDetail().getJobClass());
//
//        JobDetail jobDetail = je.getJobDetail();
//        JobDataMap jobData = jobDetail.getJobDataMap();
//        Set tjobSet =  jobData.keySet();
//        Iterator tIterator = tjobSet.iterator();
//        while(tIterator.hasNext()) {
//            String key = (String)tIterator.next();
//            System.out.println(jobData.get(key));
//        }
//    }
//}
