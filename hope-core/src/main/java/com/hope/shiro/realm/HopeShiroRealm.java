package com.hope.shiro.realm;

import cn.hutool.core.util.ObjectUtil;
import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysUser;
import com.hope.service.SysResourceService;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Hope自定义Ream(加强版)
 *
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-29 13:14
 **/
public class HopeShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(HopeShiroRealm.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    /***
     * 认证，提供账户信息，返回认证用户的角色信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取用户账号 此处有坑，需谨慎
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();

        SysUser sysuser = sysUserService.selectUserByName(username);

        if (ObjectUtil.isNull(sysuser)) {
            throw new UnknownAccountException("帐号不存在！");
        }
        if (SysUserStatusEnum.DISABLE.getCode().equals(sysuser.getStatus())) {
            throw new LockedAccountException("账号锁定，禁止登录系统，啦啦啦，得玛西亚！");
        }
        //如果认证报错了 https://blog.csdn.net/tom9238/article/details/79711651 推荐看看这篇文章
        return new SimpleAuthenticationInfo(
                sysuser,
                sysuser.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    /***
     * 授权，为当前登陆的用户授予权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        if (principalCollection == null) {
            throw new AuthorizationException("principals should not be null");
        }

        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = new HashSet<String>();
        Set<String> resources = new HashSet<String>();

        //根据用户id获取角色，资源
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();

        roles = sysRoleService.findRoleByUserId(sysUser.getId());
        resources = sysResourceService.findPermsByUserId(sysUser.getId());

        //将角色，权限添加到SimpleAuthorizationInfo认证对象中
        info.setRoles(roles);
        info.setStringPermissions(resources);
        log.info("[当前登录用户授权完成,用户id]-[{}]", sysUser.getId());
        return info;
    }

    /***
     * 清除认证信息
     * @param userIds
     */
    public void removeCachedAuthenticationInfo(List<String> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        HopeShiroRealm hopeShiroReam = (HopeShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            hopeShiroReam.clearCachedAuthenticationInfo(collection);
        }
    }

    /**
     * 清除授权信息
     *
     * @param userIds
     */
    public void clearAuthorizationByUserId(List<String> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        HopeShiroRealm realm = (HopeShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            realm.clearCachedAuthorizationInfo(collection);
        }
        log.info("[用户权限缓存更新成功]");
    }

    /***
     * 通过ids获取所有spc
     * @param userIds
     * @return
     */
    private List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds) {
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户id
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof SysUser) {
                    SysUser user = (SysUser) obj;
                    if (null != user && userIds.contains(user.getId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }

}
