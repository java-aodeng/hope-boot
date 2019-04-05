package com.hope.service;

import com.hope.model.beans.UserInfo;
import com.hope.model.result.ReturnT;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-04-01 13:12
 **/
public interface UserService {

    public ReturnT<UserInfo> findUser(String username, String password);
}
