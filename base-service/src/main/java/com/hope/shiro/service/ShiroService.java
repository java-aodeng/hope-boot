package com.hope.shiro.service;

import com.hope.model.beans.SysUser;

import java.util.Map;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-17 13:25
 **/
public interface ShiroService {
    /***
     * 初始化权限
     * @return
     */
    Map<String, String> loadFilterChainDefinitions();

    /***
     * 重新加载权限
     */
    void updatePermission();

    /***
     * 重新加载用户权限
     * @param user
     */
    void reloadAuthorizingByUserId(SysUser user);

    /***
     * 重新加载所有拥有roleId角色的用户权限
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Integer roleId);
}