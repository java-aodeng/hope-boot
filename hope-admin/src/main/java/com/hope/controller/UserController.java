package com.hope.controller;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.object.PageResultVo;
import com.hope.service.SysUserService;
import com.hope.utils.ResultHopeUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-10 20:13
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @RequiresPermissions("user:user:view")
    @GetMapping("/user")
    public ModelAndView user(){
        return ResultHopeUtil.view("admin/user/user");
    }

    /**用户列表**/
    //@RequiresPermissions("user:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo list(UserConditionVo vo){
        PageInfo<SysUser> pageInfo=sysUserService.findPageBreakByCondition(vo);
        return ResultHopeUtil.tablePage(pageInfo);
    }
}
