package com.hope.service;

import com.hope.model.beans.SysUserRole;
import com.hope.mybatis.service.BaseService;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:11
 **/
public interface SysUserRoleService extends BaseService<SysUserRole> {
    /***
     * 添加用户角色
     * @param userId
     * @param roleIds
     */
    void addUserRole(Integer userId, String roleIds);
}
