package com.hope.service;

import com.hope.model.beans.SysUser;
import com.hope.model.dto.User;
import com.hope.mybatis.service.BaseService;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysUserService extends BaseService<SysUser>{
    /***
     * 根据用户名查询
     * @param username
     * @return
     */
    User getByUserName(String username);

    /***
     * 根据角色id查询用户列表
     * @return
     */
    List<SysUser> listUsersByRoleId(Integer roleId);

    /***
     * 根据主键查询
     * @param integer
     * @return
     */
    SysUser getByPrimaryKey(Integer integer);

    /***
     * 更新用户的最后一次的登录状态
     * @param sysUser
     * @return
     */
    SysUser updateUserLastLoginInfo(SysUser sysUser);
}
