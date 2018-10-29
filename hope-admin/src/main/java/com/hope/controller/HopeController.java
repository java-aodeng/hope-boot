package com.hope.controller;

import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 17:13
 **/
@Controller
@RequestMapping("/hope")
public class HopeController {
    private static final Logger log= LoggerFactory.getLogger(HopeController.class);

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        return ResultHopeUtil.view("admin/login");
    }

    /***
     * 登录
     * @param username
     * @param password
     * @param rememberMe
     * @param s
     * @return
     */
    @PostMapping("/inlogin")
    public ResponseVo inlogin(String username,String password,boolean rememberMe,String s){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultHopeUtil.success("登录成功！");
        }catch (LockedAccountException e){
            token.clear();
            return ResultHopeUtil.error("用户已经被锁定不能登录，请联系管理员！");
        }catch (AuthenticationException e){
            token.clear();
            return ResultHopeUtil.error("用户名或者密码错误！");
        }
    }
    @RequestMapping("/index")
    public String index(Model model){
        log.info("[hope-index-page]-[{}]","测试120");
        return "admin/index";
    }
    @RequestMapping("/index_v1")
    public ModelAndView index_v1(Model model){
        log.info("[hope-index_v1-page]-[{}]","测试130");
        return ResultHopeUtil.view("admin/index_v1");
    }
    @RequestMapping("/index_v2")
    public ModelAndView index_v2(Model model){
        log.info("[hope-index_v2-page]-[{}]","测试140");
        return ResultHopeUtil.view("admin/index_v2");
    }
    @RequestMapping("/error1")
    public ModelAndView error1(Model model){
        log.info("[hope-404-page]-[{}]","测试150");
        return ResultHopeUtil.view("common/error/404");
    }
}
