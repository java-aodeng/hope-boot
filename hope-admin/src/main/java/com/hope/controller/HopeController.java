package com.hope.controller;

import com.hope.util.ResultHopeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/login")
    public ModelAndView login(Model model){
        log.info("[hope-login-page]-[{}]","测试110");
        return ResultHopeUtil.view("admin/login");
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
}
