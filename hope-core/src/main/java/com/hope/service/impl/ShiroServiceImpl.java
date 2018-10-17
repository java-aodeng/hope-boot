package com.hope.service.impl;

import com.hope.beans.SysResource;
import com.hope.beans.SysUser;
import com.hope.holder.SpringContextHolder;
import com.hope.service.ShiroService;
import com.hope.service.SysResourceService;
import com.hope.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 13:35
 **/
@Service
public class ShiroServiceImpl implements ShiroService{

    private static final Logger log= LoggerFactory.getLogger(ShiroServiceImpl.class);

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysUserService sysUserService;

    /***
     * 初始化权限
     * @return
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        /***
         * 配置访问权限
         * anon:所有url都都可以匿名访问
         * authc: 需要认证才能进行访问（此处指所有非匿名的路径都需要登陆才能访问），支付等,建议使用authc权限
         * user:配置记住我或认证通过可以访问
         */
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置shiro过滤器
        filterChainDefinitionMap.put("/hope/logout","logout");//退出过滤器，shiro代码自动实现
        filterChainDefinitionMap.put("/hope/login","anon");
        filterChainDefinitionMap.put("/hope/signin","anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/hope-static/**","anon");
        //加载数据库中配置的资源权限列表
        List<SysResource> sysResourcesList=sysResourceService.listUrlAndPermission();
        for(SysResource sysResource:sysResourcesList){
            if(!StringUtils.isEmpty(sysResource.getUrl()) && !StringUtils.isEmpty(sysResource.getPermission())){
                String permission ="perms["+sysResource.getPermission()+"]";
                filterChainDefinitionMap.put(sysResource.getUrl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**","user");
        log.info("[hope初始化权限成功,数据库资源条数]-[{}]",sysResourcesList.size());
        return filterChainDefinitionMap;
    }

    /***
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        ShiroFilterFactoryBean shiroFilterFactoryBean= SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        synchronized (shiroFilterFactoryBean){
            AbstractShiroFilter abstractShiroFilter =null;
            try{
                abstractShiroFilter =(AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            }catch (Exception e) {
                throw new RuntimeException("Get AbstractShiroFilter error");
            }
            PathMatchingFilterChainResolver pathMatchingFilterChainResolver=(PathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager defaultFilterChainManager=(DefaultFilterChainManager)pathMatchingFilterChainResolver.getFilterChainManager();
            //清空老的权限控制
            defaultFilterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            //重新构建生成
            Map<String,String> map=shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for(Map.Entry<String,String> stringEntry:map.entrySet()){
                String url=stringEntry.getKey();
                String chainDefinition=stringEntry.getValue().trim().replace(" ","");
                defaultFilterChainManager.createChain(url,chainDefinition);
            }
        }
        log.info("[hope重新加载权限成功,低调小熊猫博客：https://aodeng.cc]");
    }

    /***
     * 重新加载用户权限
     * @param sysUser
     */
    @Override
    public void reloadAuthorizingByUserId(SysUser sysUser) {
        RealmSecurityManager realmSecurityManager=(RealmSecurityManager) SecurityUtils.getSecurityManager();
        //ShiroRea
/*
        ShiroRealm shiroRealm = (ShiroRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user.getId(), realmName);
        subject.runAs(principals);
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();

        LOG.info("用户[{}]的权限更新成功！！", user.getUsername());*/
    }

    /***
     * 重新加载所有拥有roleId角色的用户权限
     * @param roleId
     */
    @Override
    public void reloadAuthorizingByRoleId(Integer roleId) {

    }
}
