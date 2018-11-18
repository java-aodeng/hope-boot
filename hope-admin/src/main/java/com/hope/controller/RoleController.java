package com.hope.controller;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.object.PageResultVo;
import com.hope.service.SysRoleService;
import com.hope.utils.ResultHopeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
 * @create:2018-10-24 14:56
 **/
@Controller
@RequestMapping("/role")
public class RoleController {
    private static final Logger log= LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/role")
    public ModelAndView role(Model model){
        return ResultHopeUtil.view("admin/role/role");
    }

    @PostMapping("/list")
    @ResponseBody
    public PageResultVo list(RoleConditionVo vo){
        PageInfo<SysRole> pageInfo=sysRoleService.findPageBreakByCondition(vo);
        log.info("[role-list-page]-[{}]","测试200");
        return ResultHopeUtil.tablePage(pageInfo);
    }

    @GetMapping("/add")
    public ModelAndView add(Model model){
        log.info("[role-add-page]-[{}]","测试210");
        return ResultHopeUtil.view("admin/role/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(SysRole sysRole, ModelMap map){
        map.addAttribute("role",sysRoleService.selectById(sysRole));
        return ResultHopeUtil.view("admin/role/edit");
    }
}
