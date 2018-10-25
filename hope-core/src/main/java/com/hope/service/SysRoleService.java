package com.hope.service;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.dto.Role;
import com.hope.model.vo.RoleConditionVo;
import com.hope.mybatis.service.BaseService;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysRoleService extends BaseService<SysRole>{
    /***
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<Role> listRolesByUserId(Integer userId);

    /***
     * 分页查询，使用分页插件
     * @param vo
     * @return
     */
    PageInfo<Role> findPageBreakByCondition(RoleConditionVo vo);
}
