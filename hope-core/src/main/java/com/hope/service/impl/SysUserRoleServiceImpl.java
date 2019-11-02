package com.hope.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.hope.mapper.SysUserRoleMapper;
import com.hope.model.beans.SysUserRole;
import com.hope.model.dto.UserRole;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /***
     * 数据类型转换
     * @param sysUserRoles
     * @return
     */
    private List<UserRole> getUserRoles(List<SysUserRole> sysUserRoles) {
        if (CollectionUtils.isEmpty(sysUserRoles)) {
            return null;
        }
        List<UserRole> userRoleList = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoles) {
            userRoleList.add(new UserRole(sysUserRole));
        }
        return userRoleList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addUserRole(Integer userId, String roleIds) {
        //参考代码https://github.com/coder-yqj/springboot-shiro/blob/master/src/main/java/com/study/service/impl/UserRoleServiceImpl.java
        //删除
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        sysUserRoleMapper.deleteByExample(example);
        //添加
        String[] roleIdss = roleIds.split(",");
        for (String roleId : roleIdss) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(Convert.convert(String.class, userId));
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }
}
