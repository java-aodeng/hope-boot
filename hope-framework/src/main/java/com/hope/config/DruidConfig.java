package com.hope.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.hope.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 11:32
 **/
@Configuration
public class DruidConfig {
    @Autowired
    private DruidProperties druidProperties;

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), druidProperties.getServletPath());
        //ip黑名单，共同存在时，deny优先于allow：如果满足deny的话提示:Sorry, you are not permitted to view this page
        List<String> denyIps = druidProperties.getDenyIps();
        if (!CollectionUtils.isEmpty(denyIps)) {
            bean.addInitParameter("deny", StringUtils.collectionToDelimitedString(denyIps, ","));
        }
        //ip白名单
        List<String> allowIps = druidProperties.getAllowIps();
        if (!CollectionUtils.isEmpty(allowIps)) {
            bean.addInitParameter("allow", StringUtils.collectionToDelimitedString(denyIps, ","));
        }
        //登陆查看信息的账号and密码
        bean.addInitParameter("loginUsername", druidProperties.getUsername());
        bean.addInitParameter("loginPassword", druidProperties.getPassword());
        //禁止html页面上的“Reset All”功能(默认false)
        bean.addInitParameter("resetEnable", String.valueOf(druidProperties.getResetEnable()));
        return bean;
    }

    /***
     * Druid的StatFilter
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //配置过滤规则
        bean.addUrlPatterns("/");
        //配置排除的url
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}