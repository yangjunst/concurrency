package com.wangwenjun.concurrent.juc.executor;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobTrigger
{

    private static final Logger LOG = LoggerFactory.getLogger(QuartzJobTrigger.class);

    public static void main(String[] args) throws SchedulerException
    {
        JobDetail jobDetail = JobBuilder.newJob(QuartzSimpleJob.class)
                .withDescription("Simple Job")
                .withIdentity("JobName", "Job's Group")
                .build();
        LOG.info("Job details {}", jobDetail);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("simple trigger for test")
                .withIdentity("Trigger's Name", "Trigger's Group")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10))
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
