package com.hope.controller;

import com.hope.service.UserService;
import com.xxl.sso.core.login.SsoWebLoginHelper;
import com.xxl.sso.core.user.XxlSsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:hope-plus
 * @ClassName:WebController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-01 13:47
 * @Description: sso server (for web)
 * @Version 1.0
 **/
@Controller
public class WebController {

    @Autowired
    private UserService userService;

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
}
