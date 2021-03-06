package com.kotall.learn.job.controller;

import com.kotall.learn.job.job.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/3/20
 */
@Slf4j
@RestController
@RequestMapping("/cronjob")
public class CronJobController {

    private static final String JOB_GROUP = "cron_job_group";
    private static final String TRIGGER_GROUP = "cron_trigger_group";

    @Autowired
    private Scheduler scheduler;

    @RequestMapping("/start")
    public String start() {
        try {

            JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("CRON_JOB", JOB_GROUP).build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CRON_TRIGGER", TRIGGER_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *")).build();

            scheduler.scheduleJob(jobDetail, trigger);

            log.info("simple job started.");
        } catch (Exception e) {
            log.error("failed to start job", e);
        }

        return "start success";
    }

    @RequestMapping("/pause")
    public String pause() {
        try {
            JobKey jobKey = new JobKey("CRON_JOB", JOB_GROUP);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("failed to pause job", e);
        }
        return "pause success";
    }

    @RequestMapping("/stop")
    public String stop() {
        try {
            JobKey jobKey = new JobKey("CRON_JOB", JOB_GROUP);
            this.scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {

        }
        return "stop success";
    }


    @RequestMapping("/resume")
    public String resume() {
        try {
            JobKey jobKey = new JobKey("CRON_JOB", JOB_GROUP);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("failed to resume job", e);
        }
        return "resume success";
    }


}
