package com.hope.shiro.realm;

import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysUser;
import com.hope.model.dto.User;
import com.hope.service.SysResourceService;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import com.hope.shiro.service.impl.ShiroServiceImpl;
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

/**Hope自定义Ream(加强版)
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-29 13:14
 **/
public class HopeShiroReam extends AuthorizingRealm{

    private static final Logger log= LoggerFactory.getLogger(HopeShiroReam.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired(required = false)
    private RedisSessionDAO redisSessionDAO;

    /***
     * 认证，提供账户信息，返回认证用户的角色信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        String username=(String) token.getPrincipal();
        /*System.out.println(username+"++++++++++++++");
        System.out.println("token信息："+token.getCredentials());*/
        SysUser sysuser=sysUserService.getByUserName(username);
        if (sysuser == null){
            throw new UnknownAccountException("帐号不存在！");
        }
        if (null != sysuser.getStatus() && SysUserStatusEnum.DISABLE.getCode().equals(sysuser.getStatus())){
            throw new LockedAccountException("账号锁定，禁止登录hope，自己好好想想为什么吧！");
        }
        //如果认证报错了 https://blog.csdn.net/tom9238/article/details/79711651 推荐看看这篇文章
        return new SimpleAuthenticationInfo(
                sysuser.getId(),
                sysuser.getPassword(),
                ByteSource.Util.bytes(sysuser.getCredentialsSalt()),
                getName()
        );
    }

    /***
     * 授权，为当前登陆的用户授予权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){

        if (principalCollection == null){
            throw new AuthorizationException("principals should not be null");
        }

        //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        Set<String> roles=new HashSet<String>();
        Set<String> resources=new HashSet<String>();

        //根据用户id获取角色，资源
        Integer userId = (Integer) SecurityUtils.getSubject().getPrincipal();
        roles=sysRoleService.findRoleByUserId(userId);
        resources=sysResourceService.findPermsByUserId(userId);

        //将角色，权限添加到SimpleAuthorizationInfo认证对象中
        info.setRoles(roles);
        info.setStringPermissions(resources);
        log.info("[当前登录用户授权完成,用户id]-[{}]",userId);
        return info;
    }

    /***
     * 清除认证信息
     * @param userIds
     */
    public void removeCachedAuthenticationInfo(List<String> userIds){
        if (null ==userIds || userIds.size() == 0){
            return;
        }
        List<SimplePrincipalCollection> list =getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager=(RealmSecurityManager) SecurityUtils.getSecurityManager();
        HopeShiroReam hopeShiroReam=(HopeShiroReam) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection:list){
            hopeShiroReam.clearCachedAuthenticationInfo(collection);
        }
    }

    /**
     * 清除授权信息
     * @param userIds
     */
    public void clearAuthorizationByUserId(List<String> userIds){
        if(null == userIds || userIds.size() == 0)	{
            return ;
        }
        List<SimplePrincipalCollection> list = getSpcListByUserIds(userIds);
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        HopeShiroReam realm = (HopeShiroReam)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection collection : list) {
            realm.clearCachedAuthorizationInfo(collection);
        }
    }

    /***
     * 通过ids获取所有spc
     * @param userIds
     * @return
     */
    private List<SimplePrincipalCollection> getSpcListByUserIds(List<String> userIds){
        //获取所有session
        Collection<Session> sessions=redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list=new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息
            Object obj=session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection){
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户id
                obj =spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof  User){
                    User user=(User) obj;
                    if (null != user && userIds.contains(user.getUserUuid())){
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }

}
