package com.hope.controller;

import com.hope.util.ResultHopeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-24 14:56
 **/
@Controller
@RequestMapping("/role")
public class RoleController {

    @RequestMapping("role")
    public ModelAndView role(Model model){
        return ResultHopeUtil.view("admin/role/role");
    }
}
