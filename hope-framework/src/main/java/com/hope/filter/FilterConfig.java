package com.hope.filter;

import com.cxytiandi.encrypt.core.EncryptionConfig;
import com.cxytiandi.encrypt.core.EncryptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @program:hope-plus
 * @ClassName:FilterConfig
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-15 20:27
 * @Description: 配置加解密过滤器
 * @Version 1.0
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<EncryptionFilter> filterRegistration() {
        EncryptionConfig config = new EncryptionConfig();
        config.setKey("abcdef0123456789");
        config.setRequestDecyptUriList(Arrays.asList("/save", "/decryptEntityXml"));
        config.setResponseEncryptUriList(Arrays.asList("/encryptStr", "/encryptEntity", "/save", "/encryptEntityXml", "/decryptEntityXml"));
        FilterRegistrationBean<EncryptionFilter> registration = new FilterRegistrationBean<EncryptionFilter>();
        registration.setFilter(new EncryptionFilter(config));
        registration.addUrlPatterns("/*");
        registration.setName("EncryptionFilter");
        registration.setOrder(1);
        return registration;
    }

}
