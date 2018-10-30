package com.hope.controller;

import com.google.code.kaptcha.Constants;
import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 17:13
 **/
@Controller
@RequestMapping(value ="/hope")
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
     * @param
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request,String username,String password,String verification,
                            @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe){
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            //验证码通过
        } else {
            log.info("验证码错误");
            return ResultHopeUtil.error("验证码错误！");
        }
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        Subject subject= SecurityUtils.getSubject();
        try {
            token.setRememberMe(1 == rememberMe);
            subject.login(token);

        }catch (LockedAccountException e){
            token.clear();
            log.info("用户已经被锁定不能登录");
            return ResultHopeUtil.error("用户已经被锁定不能登录，请联系管理员！");
        }catch (AuthenticationException e){
            token.clear();
            log.info("用户名或者密码错误");
            return ResultHopeUtil.error("用户名或者密码错误！");
        }
        log.info("登录成功");
        return ResultHopeUtil.success("登录成功！");
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

    @RequestMapping("/logintest")
    @ResponseBody
    public ResponseVo login(){

        return ResultHopeUtil.success("登录成功！");
    }
}
