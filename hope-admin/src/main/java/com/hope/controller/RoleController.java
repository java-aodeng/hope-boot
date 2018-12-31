package com.hope.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import com.hope.shiro.service.ShiroService;
import com.hope.utils.ResultHopeUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysUserService sysUserService;

    /**角色列表**/
    @RequiresPermissions("roles")
    @GetMapping("/role")
    public ModelAndView role(){
        return ResultHopeUtil.view("admin/role/role");
    }

    @RequiresPermissions("role:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo list(RoleConditionVo vo){
        PageInfo<SysRole> pageInfo=sysRoleService.findPageBreakByCondition(vo);
        return ResultHopeUtil.tablePage(pageInfo);
    }

    /***
     * 添加角色
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(){
        log.info("[role-add-page]-[{}]","测试210");
        return ResultHopeUtil.view("admin/role/add");
    }

    @RequiresPermissions("role:add")
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysRole sysRoleForm){
        try {
            sysRoleForm.setCreatetime(DateUtil.date());
            sysRoleForm.setUpdatetime(DateUtil.date());
            sysRoleForm.setRoleId(RandomUtil.randomUUID().substring(0,7).toString());
            if(sysRoleService.insert(sysRoleForm)){
                return ResultHopeUtil.success("角色添加成功！");
            }else{
                return ResultHopeUtil.error("角色添加失败！");
            }
        }catch (Exception e) {
            log.error("[角色添加失败！]-[{}]",e.getMessage());
            return ResultHopeUtil.error("角色添加失败！");
        }
    }

    /***
     * 编辑角色
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap map){
        map.addAttribute("role",sysRoleService.selectById(id));
        return ResultHopeUtil.view("admin/role/edit");
    }

    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(SysRole sysRole){
        sysRole.setUpdatetime(DateUtil.date());
        sysRole.setRoleId(RandomUtil.randomUUID().substring(0,7).toString());
        if (sysRoleService.updateById(sysRole)){
            return ResultHopeUtil.success("角色修改成功！");
        }else {
            return ResultHopeUtil.error("角色修改失败！");
        }
    }

    /***
     * 角色分配资源
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/rule/{id}")
    public ModelAndView rule(@PathVariable("id") Integer id, Model model){
        model.addAttribute("id",id);
        return ResultHopeUtil.view("admin/role/rule");
    }

    @RequiresPermissions("role:assign")
    @PostMapping("/assign")
    @ResponseBody
    public ResponseVo assign(String id, String[] menuIds){
        System.out.println(menuIds+"--------"+id);
        List<String> resourceIds=new ArrayList<>();
        if (menuIds.length!=0){
            resourceIds= Arrays.asList(menuIds);
        }
        ResponseVo responseVo=sysRoleService.addAssignResourceById(id,resourceIds);
        //重新加载拥有角色的资源权限
        shiroService.reloadAuthorizingByRoleId(Convert.convert(Integer.class,id));
        return responseVo;
    }

    /***
     * 删除角色
     * @param id
     * @return
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public ResponseVo delete(@PathVariable("id") Integer id){
        if (sysUserService.findByRoleId(id).size()>0){
            return ResultHopeUtil.error("当前角色存在用户，不能删除！");
        }
        if (sysRoleService.deleteById(id)){
            return ResultHopeUtil.success("角色删除成功！");
        }else {
            return ResultHopeUtil.error("角色删除失败！");
        }
    }
}
