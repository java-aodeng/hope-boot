package com.hope.quartz.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author:aodeng(低调小熊猫)
 * @blog:（http://ilovey.live)
 * @Description: TODO
 * @Date: 19-5-16
 **/
@Data
public class QuartzJobsVO {
    private String jobDetailName;
    private String jobCronExpression;
    private String timeZone;
    private String groupName;
    private List<String> recentTriggerTime;
}
