package com.hope.shiro;

import com.hope.entity.Resource;
import com.hope.entity.Role;
import com.hope.entity.User;
import com.hope.enums.SysUserStatusEnum;
import com.hope.service.SysResourceService;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**Shiro-授权领域
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 15:23
 **/
public class ShiroAuthorizingRealm extends AuthorizingRealm{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleService sysRoleService;

    /***
     * 权限认证，为当前登陆的用户授予权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //权限信息对象，保存用户的角色和资源信息
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Integer userId=(Integer) SecurityUtils.getSubject().getPrincipal();
        //赋予角色名称
        List<Role> roleList=sysRoleService.listRolesByUserId(userId);
        for (Role role:roleList){
            simpleAuthorizationInfo.addRole(role.getRole());
        }
        //赋予角色权限
        List<Resource> resourceList=sysResourceService.listResourcesByUserId();
        return null;
    }
/*
*
* @Override
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
    }*/
    /***
     * 提供账户信息，返回认证用户的角色信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户账号
        String sysusername=(String) authenticationToken.getPrincipal();
        User user=sysUserService.getByUserName(sysusername);
        if (user == null){
            throw new UnknownAccountException("帐号不存在！");
        }
        if (null != user.getStatus() && SysUserStatusEnum.DISABLE.getCode().equals(user.getStatus())){
            throw new LockedAccountException("账号锁定，禁止登录hope，自己好好想想为什么吧！");
        }
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(sysusername),
                getName()
        );
    }
}
