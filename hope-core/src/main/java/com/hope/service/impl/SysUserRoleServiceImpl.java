package com.hope.service.impl;

import com.hope.model.beans.SysUserRole;
import com.hope.model.dto.UserRole;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService{

    /***
     * 数据类型转换
     * @param sysUserRoles
     * @return
     */
    private List<UserRole> getUserRoles(List<SysUserRole> sysUserRoles){
        if (CollectionUtils.isEmpty(sysUserRoles)){
            return null;
        }
        List<UserRole> userRoleList=new ArrayList<>();
        for (SysUserRole sysUserRole:sysUserRoles){
            userRoleList.add(new UserRole(sysUserRole));
        }
        return userRoleList;
    }
}
