package com.kuark.tool.advance.advance20191220Algorithm;

import com.kuark.tool.advance.advance20191220Algorithm.vo.XxlJobInfo;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author rock
 * @detail cron执行规则
 * @date 2020/7/22 9:52
 */
public class CronParse {
    private static Logger logger= LoggerFactory.getLogger(CronParse.class);
    private Thread scheduleThread;
    public static final long PRE_READ_MS = 5000;    // pre read
    private volatile boolean scheduleThreadToStop = false;
    public void start(){
        /**
         * 线程启动，循环执行
         * 1、查询定时任务列表
         * 2、判断下次执行时间
         * 3、满足，异步调用具体定时任务
         * 4、计算下次执行时间（使用quartz包中的CronExpression解析cron表达式）
         */

        scheduleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!scheduleThreadToStop) {

                    // Scan Job
                    long start = System.currentTimeMillis();

                    Connection conn = null;
                    Boolean connAutoCommit = null;
                    PreparedStatement preparedStatement = null;

                    boolean preReadSuc = true;
                    try {
                        // 1、pre read
                        long nowTime = System.currentTimeMillis();
                        //todo 加载定时任务信息列表+查询下次执行时间<=now+PRE_READ_MS
                        List<XxlJobInfo> scheduleList = new ArrayList<>();
                        if (scheduleList!=null && scheduleList.size()>0) {
                            // 2、push time-ring
                            for (XxlJobInfo jobInfo: scheduleList) {

                                // time-ring jump
                                if (nowTime > jobInfo.getTriggerNextTime() + PRE_READ_MS) {
                                    // 2.1、trigger-expire > 5s：pass && make next-trigger-time

                                    // fresh next
                                    refreshNextValidTime(jobInfo, new Date());

                                } else if (nowTime > jobInfo.getTriggerNextTime()) {
                                    // 2.2、trigger-expire < 5s：direct-trigger && make next-trigger-time

                                    CronExpression cronExpression = new CronExpression(jobInfo.getJobCron());
                                    long nextTime = cronExpression.getNextValidTimeAfter(new Date()).getTime();

                                    // 1、trigger
                                    //多线程执行具体定时任务
//                                    JobTriggerPoolHelper.trigger(jobInfo.getId(), TriggerTypeEnum.CRON, -1, null, null);
                                    logger.debug(">>>>>>>>>>> xxl-job, shecule push trigger : jobId = " + jobInfo.getId() );

                                    // 2、fresh next
                                    jobInfo.setTriggerLastTime(jobInfo.getTriggerNextTime());
                                    jobInfo.setTriggerNextTime(nextTime);
                                    // next-trigger-time in 5s, pre-read again
                                    //在5秒之内，更新。。。
                                    if (jobInfo.getTriggerNextTime() - nowTime < PRE_READ_MS) {

                                        // 1、make ring second
                                        int ringSecond = (int)((jobInfo.getTriggerNextTime()/1000)%60);

                                        // 2、push time ring

                                        // 3、fresh next
                                        refreshNextValidTime(jobInfo, new Date(jobInfo.getTriggerNextTime()));
                                    }

                                } else {
                                    // 2.3、trigger-pre-read：time-ring trigger && make next-trigger-time

                                    // 1、make ring second
                                    int ringSecond = (int)((jobInfo.getTriggerNextTime()/1000)%60);

                                    // 2、push time ring

                                    // 3、fresh next
                                    refreshNextValidTime(jobInfo, new Date(jobInfo.getTriggerNextTime()));
                                }
                            }
                            // 3、update trigger info
                            for (XxlJobInfo jobInfo: scheduleList) {
                                //更新 xxlJobInfo 下次执行时间
                            }

                        } else {
                            preReadSuc = false;
                        }
                        // tx stop
                    } catch (Exception e) {
                        if (!scheduleThreadToStop) {
                            logger.error(">>>>>>>>>>> xxl-job, JobScheduleHelper#scheduleThread error:{}", e);
                        }
                    } finally {
                        // commit
                        // close PreparedStatement
                    }
                    long cost = System.currentTimeMillis()-start;


                    //TODO  Wait seconds, align second？？
                    if (cost < 1000) {  // scan-overtime, not wait
                        try {
                            // pre-read period: success > scan each second; fail > skip this period;
                            TimeUnit.MILLISECONDS.sleep((preReadSuc?1000:PRE_READ_MS) - System.currentTimeMillis()%1000);
                        } catch (InterruptedException e) {
                            if (!scheduleThreadToStop) {
                                logger.error(e.getMessage(), e);
                            }
                        }
                    }

                }//while

                logger.info(">>>>>>>>>>> xxl-job, JobScheduleHelper#scheduleThread stop");
            }
        });
        scheduleThread.setDaemon(true);
        scheduleThread.setName("xxl-job, admin JobScheduleHelper#scheduleThread");
        scheduleThread.start();
    }

    private void refreshNextValidTime(XxlJobInfo jobInfo, Date fromTime) throws ParseException {
        Date nextValidTime = new CronExpression(jobInfo.getJobCron()).getNextValidTimeAfter(fromTime);
        if (nextValidTime != null) {
            jobInfo.setTriggerLastTime(jobInfo.getTriggerNextTime());
            jobInfo.setTriggerNextTime(nextValidTime.getTime());
        } else {
            jobInfo.setTriggerStatus(0);
            jobInfo.setTriggerLastTime(0);
            jobInfo.setTriggerNextTime(0);
        }
    }
}
