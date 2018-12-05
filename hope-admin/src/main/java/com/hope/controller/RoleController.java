package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.utils.ResultHopeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysRole sysRole){
        try {
            sysRole.setCreatetime(DateUtil.date());
            if(sysRoleService.insert(sysRole)){
                return ResultHopeUtil.success("角色添加成功！");
            }else{
                return ResultHopeUtil.success("角色添加失败！");
            }
        }catch (Exception e) {
            log.error(String.format("RoleController.addRole%s", e));
            throw e;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap map){
        map.addAttribute("role",sysRoleService.selectById(id));
        return ResultHopeUtil.view("admin/role/edit");
    }

    @GetMapping("/rule")
    public ModelAndView rule(){
        return ResultHopeUtil.view("admin/role/rule");
    }
}
