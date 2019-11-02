package com.hope.controller;

import com.hope.model.beans.UserInfo;
import com.hope.model.result.ReturnT;
import com.hope.service.UserService;
import com.xxl.sso.core.conf.Conf;
import com.xxl.sso.core.login.SsoWebLoginHelper;
import com.xxl.sso.core.store.SsoLoginStore;
import com.xxl.sso.core.store.SsoSessionIdHelper;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @program:hope-boot
 * @ClassName:WebController
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @create:2019-04-01 13:47
 * @Description: sso server (for web)
 * @Version 1.0
 **/
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    /**
     * @Description: /
     * @Param: [model, request, response]
     * @return: [model, request, response]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        //login chenck
        XxlSsoUser xxlSsoUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlSsoUser == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("xxlUser", xxlSsoUser);
            return "index";
        }
    }

    /**
     * @Description: /login
     * @Param: [model, request, response]
     * @return: [model, request, response]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping(Conf.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        //login check
        XxlSsoUser xxlSsoUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlSsoUser != null) {

            //success redirect
            String redirectUrl = request.getParameter(Conf.REDIRECT_URL);

            if (redirectUrl != null && redirectUrl.trim().length() > 0) {

                String sessionId = SsoWebLoginHelper.getSessionIdByCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;

                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return "login";
    }

    /**
     * @Description: /doLogin
     * @Param: [request, response, redirectAttributes, username, password, ifRemember]
     * @return: [request, response, redirectAttributes, username, password, ifRemember]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes,
                          String username,
                          String password,
                          String ifRemember) {

        boolean ifRem = (ifRemember != null && "on".equals(ifRemember)) ? true : false;

        // valid login
        ReturnT<UserInfo> result = userService.findUser(username, password);
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            redirectAttributes.addAttribute("errorMsg", result.getMsg());

            redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
            return "redirect:/login";
        }

        // 1、make xxl-sso user
        XxlSsoUser xxlUser = new XxlSsoUser();
        xxlUser.setUserid(String.valueOf(result.getData().getUserid()));
        xxlUser.setUsername(result.getData().getUsername());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinite(SsoLoginStore.getRedisExpireMinite());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());


        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, xxlUser, ifRem);

        // 4、return, redirect sessionId
        String redirectUrl = request.getParameter(Conf.REDIRECT_URL);
        if (redirectUrl != null && redirectUrl.trim().length() > 0) {
            String redirectUrlFinal = redirectUrl + "?" + Conf.SSO_SESSIONID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }

    }

    /**
     * @Description: /logout
     * @Param: [request, response, redirectAttributes]
     * @return: [request, response, redirectAttributes]
     * @Author: aodeng
     * @Date: 2019/4/1
     */
    @RequestMapping(Conf.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // logout
        SsoWebLoginHelper.logout(request, response);

        redirectAttributes.addAttribute(Conf.REDIRECT_URL, request.getParameter(Conf.REDIRECT_URL));
        return "redirect:/login";
    }
}
