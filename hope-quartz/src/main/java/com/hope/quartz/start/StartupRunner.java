package com.hope.quartz.start;

import com.hope.quartz.schedule.CronScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（http://ilovey.live)
 * @Description: 有两种方案来触发 CronSchedule 定时任务，一种是启动时调用 scheduleJobs() 来启动定时任务，另外一种方案使用 Spring Boot 自带的 Scheduled 在特定时间触发启动
 * 这里使用 启动时触发定时任务
 * @Date: 19-5-15
 **/
@Component
public class StartupRunner implements CommandLineRunner {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private CronScheduleJob cronScheduleJob;

    @Override
    public void run(String... args) throws Exception {
        cronScheduleJob.scheduleJobsRun();
        LOGGER.info(">>>>>>>>>>scheduleJobsRun 定时任务启动！<<<<<<<<<<<");
    }
}
