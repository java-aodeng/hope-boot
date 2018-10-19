package com.hope.service.impl;

import com.hope.model.beans.SysUser;
import com.hope.model.entity.User;
import com.hope.mapper.SysUserMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserService;
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
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    /***
     * 数据类型转换为业务类型
     * @param sysUsers
     * @return
     */
    private List<User> getUsers(List<SysUser> sysUsers){
        if (CollectionUtils.isEmpty(sysUsers)){
            return null;
        }
        List<User> userList=new ArrayList<>();
        for(SysUser sysUser:sysUsers){
            userList.add(new User(sysUser));
        }
        return userList;
    }

    /***
     * 根据用户名查询
     * @param username
     * @return
     */
    @Override
    public User getByUserName(String username) {
        User user=new User(username,"");
        SysUser sysUser=selectOne(user.getSysUser());
        return null == sysUser ? null : new User(sysUser);
    }

    @Override
    public List<User> listUsersByRoleId(Integer roleId) {
        return getUsers(sysUserMapper.listUsersByRoleId(roleId));
    }
}
