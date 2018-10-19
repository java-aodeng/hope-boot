package com.hope.service.impl;

import com.hope.model.beans.SysRole;
import com.hope.model.entity.Role;
import com.hope.mapper.SysRoleMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService{

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
