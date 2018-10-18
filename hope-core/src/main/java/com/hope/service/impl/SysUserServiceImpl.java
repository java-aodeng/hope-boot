package com.hope.service.impl;

import com.hope.entity.User;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserService;
import org.springframework.stereotype.Service;

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
