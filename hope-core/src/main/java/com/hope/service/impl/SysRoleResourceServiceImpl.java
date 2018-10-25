package com.hope.service.impl;

import com.hope.model.beans.SysRoleResource;
import com.hope.model.dto.RoleResource;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**业务实现类，注意：使用通用实现类需用数据对象(原因蛋痛)
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysRoleResourceServiceImpl extends BaseServiceImpl<SysRoleResource> implements SysRoleResourceService{

    /***
     * 数据类型转换为业务类型
     * @param sysRoleResources
     * @return
     */
    private List<RoleResource> getRoleResources(List<SysRoleResource> sysRoleResources){
        if (CollectionUtils.isEmpty(sysRoleResources)){
            return null;
        }
        List<RoleResource> roleResourceList=new ArrayList<>();
        for (SysRoleResource sysRoleResource:sysRoleResources){
            roleResourceList.add(new RoleResource(sysRoleResource));
        }
        return roleResourceList;
    }
}
