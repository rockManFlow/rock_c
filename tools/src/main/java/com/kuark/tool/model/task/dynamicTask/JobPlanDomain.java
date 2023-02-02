package com.kuark.tool.model.task.dynamicTask;

/**
 * @author caoqingyuan
 * @detail
 * @date 2018/4/10 15:29
 */
public class JobPlanDomain {
    //任务编号
    private String jobCode;
    //任务计划编号
    private String jobPlanCode;
    //任务启动时间表达式
    private String cronExpression;
    //任务启动状态 0 未启动 1 启动
    private String runState;
    //任务是否有效 0 有效 1 无效
    private String valid;
    //任务参数
    private String paramMap;
    //任务执行次数
    private int count;
    //下次任务启动时间，适用于任务启动多次
    private String nextRunTime;
    //任务开始时间
    private String startDateTime;
    //任务结束时间
    private String endDateTime;
    //执行频率,1、每天一次 2、每年一次 3、每小时一次  4、每周一次
    private int frequence;
    //任务运行的节点和端口号
    private String ip;
    //任务定制人
    private String operator;
    //任务定制日期
    private String makeDate;
    //任务定制时间
    private String makeTime;
    //任务修改日期
    private String modifyDate;
    //任务修改时间
    private String modifyTime;

    public String getJobCode() {
        return jobCode;
    }
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }
    public String getJobPlanCode() {
        return jobPlanCode;
    }
    public void setJobPlanCode(String jobPlanCode) {
        this.jobPlanCode = jobPlanCode;
    }
    public String getCronExpression() {
        return cronExpression;
    }
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    public String getValid() {
        return valid;
    }
    public void setValid(String valid) {
        this.valid = valid;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }
    public String getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getMakeDate() {
        return makeDate;
    }
    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }
    public String getMakeTime() {
        return makeTime;
    }
    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }
    public String getModifyDate() {
        return modifyDate;
    }
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getNextRunTime() {
        return nextRunTime;
    }
    public void setNextRunTime(String nextRunTime) {
        this.nextRunTime = nextRunTime;
    }
    public void setParamMap(String paramMap) {
        this.paramMap = paramMap;
    }
    public String getRunState() {
        return runState;
    }
    public void setRunState(String runState) {
        this.runState = runState;
    }
    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
    public String getParamMap() {
        return paramMap;
    }
}
