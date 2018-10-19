package com.hope.service.impl;

import com.hope.beans.SysUser;
import com.hope.entity.User;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserService;
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
public class SysUserServiceImpl extends BaseServiceImpl<User> implements SysUserService{

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
        return selectOne(user);
    }
}
