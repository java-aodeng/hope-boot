package com.hope.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（http://ilovey.live)
 * @Description: TODO
 * @Date: 19-5-15
 **/
public class TestJob1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob1 执行");
    }
}
