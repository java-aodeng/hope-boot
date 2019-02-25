package com.hope.service;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.mybatis.service.BaseService;
import com.hope.object.ResponseVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysRoleService extends BaseService<SysRole> {
    /***
     * 根据用户id查询角色列表
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);

    /***
     * 分页查询，使用分页插件
     * @param vo
     * @return
     */
    PageInfo<SysRole> findPageBreakByCondition(RoleConditionVo vo);

    /**
     * 根据用户id查询角色集合
     *
     * @param userId
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);

    ResponseVo addAssignResourceById(String roleId, List<String> resourceIds);

    /***
     * 根据用户id获取角色及选中的角色
     * @param userId
     * @return
     */
    List<Map<String, Object>> RoleListWithSelected(Integer userId);
}
