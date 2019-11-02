package com.hope.quartz.controller;

import com.hope.quartz.model.vo.QuartzJobsVO;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（http://ilovey.live)
 * @Description: TODO
 * @Date: 19-5-16
 **/
@RestController
@RequestMapping("/quartz")
public class ManageQuartzJobs {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageQuartzJobs.class);

    @Autowired
    private SchedulerFactoryBean springScheduler;

    /** 
    * @Description: 查看Quartz的定时任务
    * @Author: aodeng
    * @Date: 19-5-16
    */ 
    @RequestMapping("/get_all_jobs")
    public List<QuartzJobsVO> getAllJobs() {
        List<QuartzJobsVO> quartzJobsVOList = new ArrayList<>();
        try {
            //获取Scheduler
            Scheduler scheduler = springScheduler.getScheduler();
            //再获取Scheduler下的所有group
            List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
            for (String groupName : triggerGroupNames) {
                //组装group的匹配，为了模糊获取所有的triggerKey或者jobKey
                GroupMatcher groupMatcher = GroupMatcher.groupEquals(groupName);
                //获取所有的triggerKey
                Set<TriggerKey> triggerKeySet = scheduler.getTriggerKeys(groupMatcher);
                for (TriggerKey triggerKey : triggerKeySet) {
                    //通过triggerKey在scheduler中获取trigger对象
                    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    //获取trigger拥有的Job
                    JobKey jobKey = trigger.getJobKey();
                    JobDetailImpl jobDetail = (JobDetailImpl) scheduler.getJobDetail(jobKey);
                    List<String> getRecentTriggerTime=getRecentTriggerTime(trigger.getCronExpression());
                    //组装页面需要显示的数据
                    QuartzJobsVO quartzJobsVO = new QuartzJobsVO();
                    quartzJobsVO.setGroupName(groupName);
                    quartzJobsVO.setJobDetailName(jobDetail.getName());
                    quartzJobsVO.setJobCronExpression(trigger.getCronExpression());
                    quartzJobsVO.setTimeZone(trigger.getTimeZone().getID());
                    quartzJobsVO.setRecentTriggerTime(getRecentTriggerTime);
                    quartzJobsVOList.add(quartzJobsVO);
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取定时任务信息出错", e);
        }
        return quartzJobsVOList;
    }

    /** 
    * @Description: 查看cron表达式最近20次的运行时间
    * @Author: aodeng
    * @Date: 19-5-16
    */ 
    private List<String> getRecentTriggerTime(String cron) {
        List<String> list = new ArrayList<String>();
        try {
            CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
            cronTriggerImpl.setCronExpression(cron);
            // 这个是重点，一行代码搞定
            List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, 20);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Date date : dates) {
                list.add(dateFormat.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
