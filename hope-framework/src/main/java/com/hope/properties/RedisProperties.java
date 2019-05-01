package com.hope.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * redisProperties类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 11:35
 **/
@Component
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class RedisProperties {

    private Integer database;
    private String host;
    private Integer port;
    private String password;
    /**
     * 链接超时(毫秒)
     **/
    private Integer timeout;
    /**
     * 数据过期时间，默认30天
     **/
    private Integer expire = 2592000;

}