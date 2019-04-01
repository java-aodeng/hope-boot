package com.hope.service.impl;

import com.hope.model.beans.UserInfo;
import com.hope.model.result.ReturnT;
import com.hope.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @program:hope-plus
 * @ClassName:UserServiceImpl
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-01 13:13
 * @Description: TODO
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ReturnT<UserInfo> findUser(String username, String password) {

        if (username==null || username.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input username.");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input password.");
        }

        //根据密码，账号查询用户，返回用户信息


        return new ReturnT<UserInfo>(new UserInfo());
    }
}
