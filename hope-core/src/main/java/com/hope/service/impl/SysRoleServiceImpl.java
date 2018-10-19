package com.hope.service.impl;

import com.hope.beans.SysResource;
import com.hope.beans.SysRole;
import com.hope.entity.Resource;
import com.hope.entity.Role;
import com.hope.entity.User;
import com.hope.mapper.SysRoleMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<Role> implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /***
     * 数据类型转换为业务类型
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
    public List<Role> listRolesByUserId(Integer userId) {
        return getRoles(sysRoleMapper.listRolesByUserId(userId));
    }
}
