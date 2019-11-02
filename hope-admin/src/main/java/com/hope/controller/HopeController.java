package com.hope.controller;

import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 17:13
 **/
@Api(value = "页面跳转", description = "页面跳转管理api", position = 5, produces = "http")
@Controller
@Slf4j
public class HopeController {

    /***
     * 首页
     * @return
     */
    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = {"/", "/common/index", "/index"})
    public String index() {
        return "common/index";
    }

    /***
     * 登录
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @GetMapping("/login")
    public String login() {
        return "common/login";
    }

    /***
     * hope-boot
     * @param model
     * @return
     */
    @ApiOperation(value = "hope-boot", notes = "hope-boot")
    @GetMapping("/hope-boot")
    public ModelAndView index_v1(Model model) {
        return ResultHopeUtil.view("common/hope-boot");
    }

    /***
     * 资源列表
     * @return
     */
    @ApiOperation(value = "资源列表", notes = "资源列表")
    @RequiresPermissions("resources")
    @GetMapping("/resource/resource")
    public ModelAndView resource() {
        return ResultHopeUtil.view("admin/resource/resource");
    }

    /**
     * @Description: 打开角色列表
     * @Param: []
     * @return: []
     * @Author: aodeng
     * @Date: 19-3-17
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @RequiresPermissions("roles")
    @GetMapping("/role/role")
    public ModelAndView role() {
        return ResultHopeUtil.view("admin/role/role");
    }

    /**
     * @Description: 打开添加角色
     * @Param: []
     * @return: []
     * @Author: aodeng
     * @Date: 19-3-17
     */
    @ApiOperation(value = "打开添加角色", notes = "打开添加角色")
    @GetMapping("/role/add")
    public ModelAndView add() {
        log.info("[role-add-page]-[{}]", "测试210");
        return ResultHopeUtil.view("admin/role/add");
    }

    /**
     * 用户列表 //默认查看权限开启
     **/
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @RequiresPermissions("user:user:view")
    @GetMapping("/user/user")
    public ModelAndView user() {
        return ResultHopeUtil.view("admin/user/user");
    }

    /***
     * 添加用户
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @GetMapping("/user/add")
    public ModelAndView userAdd() {
        return ResultHopeUtil.view("admin/user/add");
    }
}