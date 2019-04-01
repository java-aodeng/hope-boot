package com.hope.controller;

import com.hope.model.beans.UserInfo;
import com.hope.model.result.ReturnT;
import com.hope.service.UserService;
import com.xxl.sso.core.login.SsoTokenLoginHelper;
import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.store.SsoSessionIdHelper;
import com.xxl.sso.core.user.XxlSsoUser;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @program:hope-plus
 * @ClassName:AppController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-01 13:25
 * @Description: TODO
 * @Version 1.0
 **/
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    /** 
    * @Description: login
    * @Param: [username, password]
    * @return: [username, password]
    * @Author: aodeng
    * @Date: 2019/4/1
    */ 
    public ReturnT<String> login(String username, String password) {

        // valid login
        ReturnT<UserInfo> result = userService.findUser(username, password);
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            return new ReturnT<String>(result.getCode(), result.getMsg());
        }

        // 1、make xxl-sso user
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(result.getData().getUserId()));
        xxlUser.setUsername(result.getData().getUserName());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinite(SsoLoginStore.getRedisExpireMinite());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());
        
        // 2、generate sessionId + storeKey
        String sessionId= SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey
        SsoTokenLoginHelper.login(sessionId,xxlUser);

        // 4、return sessionId
        return new ReturnT<String>(sessionId);
    }
}
