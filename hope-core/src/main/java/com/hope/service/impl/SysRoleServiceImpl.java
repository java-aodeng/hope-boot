package com.hope.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.dto.Role;
import com.hope.mapper.SysRoleMapper;
import com.hope.model.vo.RoleConditionVo;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /***
     * 数据类型转换
     * @param sysRoles
     * @return
     */
    private List<Role> getRoles(List<SysRole> sysRoles){
        if(CollectionUtils.isEmpty(sysRoles)){
            return null;
        }
        List<Role> roleList=new ArrayList<>();
        for (SysRole sysRole:sysRoles){
            roleList.add(new Role(sysRole));
        }
        return roleList;
    }

    @Override
    public List<SysRole> listRolesByUserId(Integer userId) {
        return sysRoleMapper.listRolesByUserId(userId);
    }

    @Override
    public PageInfo<SysRole> findPageBreakByCondition(RoleConditionVo vo) {
        PageHelper.startPage(vo.getPageNumber(),vo.getPageSize());
        List<SysRole> sysRoles=sysRoleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysRoles)){
            return null;
        }
        PageInfo list=new PageInfo<SysRole>(sysRoles);
        list.setList(getRoles(sysRoles));
        return list;
    }

    @Override
    public Set<String> findRoleByUserId(String userId) {
        return sysRoleMapper.findRoleByUserId(userId);
    }
}
