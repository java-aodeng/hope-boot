package com.hope.shiro;

import com.hope.beans.SysUser;
import com.hope.entity.User;
import com.hope.service.SysResourceService;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**Shiro-授权领域
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 15:23
 **/
public class ShiroAuthorizingRealm extends AuthorizingRealm{

    @Autowired(required = false)
    private SysUserService sysUserService;
    @Autowired(required = false)
    private SysResourceService sysResourceService;
    @Autowired(required = false)
    private SysRoleService sysRoleService;

    /***
     * 提供账户信息，返回认证信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /***
     * 提供账户信息，返回认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号
        String sysusername=(String) authenticationToken.getPrincipal();
        //User User=new User(sysusername);
        //SysUser sysUser=sysUserService.selectOne(sysusername);
        return null;
    }
}
/*
*

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    */
/**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     *//*

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<Role> roleList = roleService.listRolesByUserId(userId);
        for (Role role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<Resources> resourcesList = resourcesService.listByUserId(userId);
        if (!CollectionUtils.isEmpty(resourcesList)) {
            for (Resources resources : resourcesList) {
                String permission = resources.getPermission();
                System.out.println(resources.getName() + "   " + permission);
                if (!StringUtils.isEmpty(permission)) {
                    info.addStringPermission(permission);
                }
            }
        }
        return info;
    }

}*/
