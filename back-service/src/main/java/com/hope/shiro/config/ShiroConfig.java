package com.hope.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hope.properties.RedisProperties;
import com.hope.shiro.credentials.RetryLimitCredentialsMatcher;
import com.hope.shiro.filter.KickoutSessionControlFilter;
import com.hope.shiro.realm.HopeShiroRealm;
import com.hope.shiro.service.ShiroService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro-配置类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-20 09:33
 * @update:2018-11-1 14:12
 **/
@Configuration
@Order(-1) //注解表示加载顺序
public class ShiroConfig {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private RedisProperties redisProperties;

    /**=====================================================hope-boot ShiroConfig star=======================================**/

    /***
     * 管理shirobean生命周期
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /***
     * 创建SecurityManager
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置realm
        defaultWebSecurityManager.setRealm(hopeShiroReam());
        //注入记住我的管理器
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        // 自定义缓存实现 使用redis
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        //使用redis自定义session管理
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    /***
     * HopeShiroReam 自定义ream，认证，授权
     * @return
     */
    @Bean
    public HopeShiroRealm hopeShiroReam() {
        HopeShiroRealm hopeShiroReam = new HopeShiroRealm();
        //匹配器，credentialsMatcher使用RetryLimitCredentialsMatcher
        //hashedCredentialsMatcher使用HashedCredentialsMatcher
        //这里简洁可以使用hashedCredentialsMatcher
        //hopeShiroReam.setCredentialsMatcher(hashedCredentialsMatcher());
        hopeShiroReam.setCredentialsMatcher(credentialsMatcher());
        return hopeShiroReam;
    }

    /**
     * 凭证匹配器
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /***
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        //配置地址，端口
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPort(redisProperties.getPort());
        //配置缓存的redis库
        redisManager.setDatabase(redisProperties.getDatabase());
        //链接超时(毫秒)
        redisManager.setTimeout(redisProperties.getTimeout());
        //缓存过期时间
        /*redisManager.setExpire(redisProperties.getExpire());*/
        //配置密码
        redisManager.setPassword(redisProperties.getPassword());
        return redisManager;
    }

    /***
     * Shiro-Session管理
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /***
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /***
     * cookid管理对象，记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("1QWLxg+NYmxraMoxAXu/Iw=="));
        return cookieRememberMeManager;
    }

    /***
     * Cookid对象
     * @return
     */
    public SimpleCookie simpleCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间30天，单位秒，注释，默认永久不过期
        simpleCookie.setMaxAge(redisProperties.getExpire());
        return simpleCookie;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权的界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error1");
        //自定义拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
        //限制同一个账号同时在线的个数
        filterMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //配置数据库中的resource
        Map<String, String> filterChainDefinitionMap = shiroService.loadFilterChainDefinitions();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /***
     * 限制同一账号，登录人数的控制
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(5);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/kickout");
        return kickoutSessionControlFilter;
    }

    /***
     * 为了在thymeleaf引擎中使用shiro的标签bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /***
     * 凭证匹配器
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher credentialsMatcher() {
        return new RetryLimitCredentialsMatcher();
    }

    /**=====================================================hope-boot ShiroConfig end=======================================**/
    /**扩展**/
    /*@Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager){
        MethodInvokingFactoryBean factoryBean=new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(securityManager);
        return factoryBean;
    }*/

    /*@Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }*/

    /***
     * 自定义拦截器，重写shiro登录成功重定向
     * @return
     */
    /*public LoginFormAuthenticationFilter loginFormAuthenticationFilter(){
        LoginFormAuthenticationFilter loginFormAuthenticationFilter=new LoginFormAuthenticationFilter();
        loginFormAuthenticationFilter.setSuccessUrl("/index");
        return loginFormAuthenticationFilter;
    }*/
}
