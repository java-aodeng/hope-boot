package com.hope.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration  //1.主要用于标记配置类，兼备Component的效果
@EnableScheduling  // 2.开启定时任务
//@EnableAsync //开启多线程
public class SaticScheduleTask {
    //3.添加定时任务
    public final static long ONE_Minute =  60 * 1000;
    private static final Logger log = LoggerFactory.getLogger(SaticScheduleTask.class);

    //@Async
    @Scheduled(cron = "0 14 17 * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Async
    @Scheduled(fixedRate = ONE_Minute)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Async
    @Scheduled(fixedDelay = ONE_Minute)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }
}
