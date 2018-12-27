package com.hope.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hope.mapper.SysRoleResourceMapper;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.model.beans.SysRoleResource;
import com.hope.model.dto.Role;
import com.hope.mapper.SysRoleMapper;
import com.hope.model.vo.RoleConditionVo;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.utils.ResultHopeUtil;
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
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

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
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysRole> sysRoles=sysRoleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysRoles)){
            return null;
        }
        PageInfo list=new PageInfo<>(sysRoles);
        return list;
    }

    @Override
    public Set<String> findRoleByUserId(Integer userId) {
        return sysRoleMapper.findRoleByUserId(userId);
    }

    @Override
    public ResponseVo addAssignResourceById(String roleId, List<String> resourceIds) {
        try {
            SysRoleResource roleResource=new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResourceMapper.delete(roleResource);
            for (String resourceId:resourceIds) {
                roleResource.setId(null);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
            return ResultHopeUtil.success("分配资源成功！");
        }catch (Exception e){
            return ResultHopeUtil.error("分配资源失败！");
        }
    }
}
