package com.hope.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program:hope-boot
 * @ClassName:MyWebMvcConfigurer
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @create:2019-04-01 11:42
 * @Description: web mvc config
 * @Version 1.0
 **/
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
