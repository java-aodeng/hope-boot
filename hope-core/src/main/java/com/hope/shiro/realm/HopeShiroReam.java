package com.hope.shiro.realm;

import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysUser;
import com.hope.model.dto.User;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**Hope自定义Ream(加强版)
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-29 13:14
 **/
public class HopeShiroReam extends AuthorizingRealm{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired(required = false)
    private RedisSessionDAO redisSessionDAO;

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
        User user=(User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(sysRoleService.findRoleByUserId(user.getUserUuid()));
        info.setStringPermissions(sysResourceService.findPermsByUserId(user.getUserUuid()));
        return info;
    }
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
        User user=sysUserService.getByUserName(username);
        if (user == null){
            throw new UnknownAccountException("帐号不存在！");
        }
        if (null != user.getStatus() && SysUserStatusEnum.DISABLE.getCode().equals(user.getStatus())){
            throw new LockedAccountException("账号锁定，禁止登录hope，自己好好想想为什么吧！");
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
        return authenticationInfo;
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
