package com.hope.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-31 10:21
 **/
public class LoginFormAuthenticationFilter /*extends FormAuthenticationFilter*/ {

    /*@Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception{
        WebUtils.issueRedirect(request,response,getSuccessUrl(),null,true);
    }*/
}
