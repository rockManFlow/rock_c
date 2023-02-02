package com.kuark.tool.base.vo;

import java.util.Date;

/**
 * Created by cc on 2016/4/12.
 */
public class ArchivePojo {
    private Long id;
    private Date create_time;

    public Long getId() {
        return id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /*public static List<CronJobVirt> parseToCronJobCfg(List<HashMap<String, Object>> queryLst) {
        List<CronJobVirt> cronJobCfgs = new ArrayList<CronJobVirt>();
        for (HashMap<String, Object> emap : queryLst) {
            CronJobVirt cronJobCfg = new CronJobVirt();
            cronJobCfg.setId((Integer) emap.get("id"));
            cronJobCfg.setJobName((String) emap.get("job_name"));
            cronJobCfg.setCronExpression((String) emap.get("cron_expression"));
            cronJobCfg.setBizData((String) emap.get("biz_data"));
            cronJobCfg.setGmtModified((Date) emap.get("gmt_modified"));
            cronJobCfg.setEnable((String) emap.get("enable"));
            cronJobCfgs.add(cronJobCfg);
        }
        return cronJobCfgs;
    }*/
}
