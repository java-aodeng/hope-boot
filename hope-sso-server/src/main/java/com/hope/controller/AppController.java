package com.hope.controller;

import com.hope.model.beans.UserInfo;
import com.hope.model.result.ReturnT;
import com.hope.service.UserService;
import com.xxl.sso.core.login.SsoTokenLoginHelper;
import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.store.SsoSessionIdHelper;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program:hope-plus
 * @ClassName:AppController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-01 13:25
 * @Description: sso server (for app)
 * @Version 1.0
 **/
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;

    /**
     * @Description: Login
     * @Param: [username, password]
     * @return: [username, password]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping("/login")
    public ReturnT<String> login(String username, String password) {

        // valid login
        ReturnT<UserInfo> result = userService.findUser(username, password);
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            return new ReturnT<String>(result.getCode(), result.getMsg());
        }

        // 1、make xxl-sso user
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(result.getData().getUserid()));
        xxlUser.setUsername(result.getData().getUsername());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinite(SsoLoginStore.getRedisExpireMinite());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());

        // 2、generate sessionId + storeKey
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey
        SsoTokenLoginHelper.login(sessionId, xxlUser);

        // 4、return sessionId
        return new ReturnT<String>(sessionId);
    }

    /**
     * @Description: Logout
     * @Param: [sessionId]
     * @return: [sessionId]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping("/logout")
    public ReturnT<String> logout(String sessionId) {
        // logout, remove storeKey
        SsoTokenLoginHelper.logout(sessionId);
        return ReturnT.SUCCESS;
    }

    /**
     * @Description: Logincheck
     * @Param: [sessionId]
     * @return: [sessionId]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping("/logincheck")
    public ReturnT<XxlSsoUser> logincheck(String sessionId) {

        //logout
        XxlSsoUser xxlSsoUser = SsoTokenLoginHelper.loginCheck(sessionId);
        if (xxlSsoUser == null) {
            return new ReturnT<XxlSsoUser>(ReturnT.FAIL_CODE, "sso not login.");
        }
        return new ReturnT<XxlSsoUser>(xxlSsoUser);
    }
}
