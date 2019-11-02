package com.hope.config;

import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @program:hope-boot
 * @ClassName:SsoConfig
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @create:2019-03-31 12:30
 * @Description: TODO
 * @Version 1.0
 **/
@Configuration
@Order(-1)
public class SsoConfig implements InitializingBean,DisposableBean {

    @Value("${sso.redis.address}")
    private String redisAddress;

    @Value("${sso.redis.expire.minite}")
    private int redisExpireMinite;

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
        JedisUtil.init(redisAddress);
    }
}
