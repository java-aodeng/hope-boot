package com.hope.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.model.vo.RoleConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserService;
import com.hope.shiro.service.ShiroService;
import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "角色", description = "角色管理api", position = 20, produces = "http")
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private SysUserService sysUserService;

    /** 
    * @Description: 角色列表
    * @Param: [vo]
    * @return: [vo]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "角色列表", notes = "角色列表，传入参数只需要pageNum和pageSize",produces="application/json, application/xml", consumes="application/json, application/xml",response = SysRole.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数", required = true, dataType = "String", paramType = "query")
    })
    @RequiresPermissions("role:list")
    @PostMapping("/list")
    public PageResultVo list(RoleConditionVo vo) {
        PageInfo<SysRole> pageInfo = sysRoleService.findPageBreakByCondition(vo);
        return ResultHopeUtil.tablePage(pageInfo);
    }

    /** 
    * @Description: 保存添加角色
    * @Param: [sysRoleForm]
    * @return: [sysRoleForm]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "保存添加角色", notes = "保存添加角色",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:add")
    @PostMapping("/add")
    public ResponseVo add(SysRole sysRoleForm) {
        try {
            sysRoleForm.setCreatetime(DateUtil.date());
            sysRoleForm.setUpdatetime(DateUtil.date());
            sysRoleForm.setRoleId(RandomUtil.randomUUID().substring(0, 7).toString());
            if (sysRoleService.insert(sysRoleForm)) {
                return ResultHopeUtil.success("角色添加成功！");
            } else {
                return ResultHopeUtil.error("角色添加失败！");
            }
        } catch (Exception e) {
            log.error("[角色添加失败！]-[{}]", e.getMessage());
            return ResultHopeUtil.error("角色添加失败！");
        }
    }

    /** 
    * @Description: 打开编辑角色
    * @Param: [id, map]
    * @return: [id, map]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "打开编辑角色", notes = "打开编辑角色")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "角色主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap map) {
        map.addAttribute("role", sysRoleService.selectById(id));
        return ResultHopeUtil.view("admin/role/edit");
    }

    /** 
    * @Description: 保存编辑角色
    * @Param: [sysRole]
    * @return: [sysRole]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "保存编辑角色", notes = "保存编辑角色",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    public ResponseVo edit(SysRole sysRole) {
        sysRole.setUpdatetime(DateUtil.date());
        sysRole.setRoleId(RandomUtil.randomUUID().substring(0, 7).toString());
        if (sysRoleService.updateById(sysRole)) {
            return ResultHopeUtil.success("角色修改成功！");
        } else {
            return ResultHopeUtil.error("角色修改失败！");
        }
    }

    /** 
    * @Description: 打开角色分配资源
    * @Param: [id, model]
    * @return: [id, model]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "打开角色分配资源", notes = "打开角色分配资源")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "角色主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/rule/{id}")
    public ModelAndView rule(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return ResultHopeUtil.view("admin/role/rule");
    }

    /** 
    * @Description: 保存角色分配资源
    * @Param: [id, menuIds]
    * @return: [id, menuIds]
    * @Author: aodeng
    * @Date: 19-3-17
    */
    @ApiOperation(value = "保存角色分配资源", notes = "保存角色分配资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuIds", value = "菜单id,String类型的数组", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:assign")
    @PostMapping("/assign")
    public ResponseVo assign(String id, String[] menuIds) {
        log.info(menuIds + "--------" + id);
        List<String> resourceIds = new ArrayList<>();
        if (menuIds.length != 0) {
            resourceIds = Arrays.asList(menuIds);
        }
        ResponseVo responseVo = sysRoleService.addAssignResourceById(id, resourceIds);
        //重新加载拥有角色的资源权限
        shiroService.reloadAuthorizingByRoleId(Convert.convert(Integer.class, id));
        return responseVo;
    }

    /***
     * 删除角色
     * @param id
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Integer", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("role:delete")
    @PostMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable("id") Integer id) {
        if (sysUserService.findByRoleId(id).size() > 0) {
            return ResultHopeUtil.error("当前角色存在用户，不能删除！");
        }
        if (sysRoleService.deleteById(id)) {
            return ResultHopeUtil.success("角色删除成功！");
        } else {
            return ResultHopeUtil.error("角色删除失败！");
        }
    }
}
