package com.hope.service;

import com.hope.entity.Role;
import com.hope.mybatis.service.BaseService;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysRoleService extends BaseService<Role>{
    /***
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<Role> listRolesByUserId(Integer userId);
}
