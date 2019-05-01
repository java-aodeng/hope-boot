package com.hope.config;

import com.hope.interceptor.TTOInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-boot
 * @ClassName:MvcConfig
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-12 12:02
 * @Description: 拦截器
 * @Version 1.0
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*List<String> filterChainDefinitionMap=new ArrayList<>();
        filterChainDefinitionMap.add("/swagger-resources/**");
        filterChainDefinitionMap.add("/webjars/**");
        filterChainDefinitionMap.add("/v2/**");
        filterChainDefinitionMap.add("/swagger-ui.html/**");*/
        registry.addInterceptor(new TTOInterceptor());//.excludePathPatterns(filterChainDefinitionMap)
    }
}

