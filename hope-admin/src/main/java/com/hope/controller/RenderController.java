package com.hope.controller;

import com.hope.utils.ResultHopeUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-30 13:05
 **/
@Controller
public class RenderController {
    @RequiresAuthentication
    @GetMapping(value = {"", "/index"})
    public ModelAndView home() {
        return ResultHopeUtil.view("admin/index");
    }

}

