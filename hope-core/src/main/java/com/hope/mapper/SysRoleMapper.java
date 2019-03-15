package com.hope.mapper;

import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-16 13:59
 **/
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> listRolesByUserId(Integer userId);

    List<SysRole> findPageBreakByCondition(RoleConditionVo vo);

    /**
     * 根据用户id查询角色集合
     *
     * @param userId 用户id
     * @return set
     */
    Set<String> findRoleByUserId(Integer userId);

    /***
     * 根据用户id获取角色及选中信息
     * RoleListWithSelected
     */
    List<SysRole> RoleListWithSelected(Integer userId);
}
