package com.hope.cache.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis配置信息
 * @author aodeng-低调小熊猫
 * @since 20-4-2
 */
@Data
@Component
public class ConfigBean {
    @Value("")
    private String address;
    @Value("")
    private String flag;
    @Value("")
    private Integer port;
    @Value("")
    private Integer maxActive;
    @Value("")
    private Integer maxIdle;
    @Value("")
    private String maxWait;
    @Value("")
    private String pwd;
}
