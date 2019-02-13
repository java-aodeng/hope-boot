package com.hope.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysRole;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysRoleService;
import com.hope.service.SysUserRoleService;
import com.hope.service.SysUserService;
import com.hope.shiro.realm.HopeShiroRealm;
import com.hope.utils.ResultHopeUtil;
import com.hope.utils.UsingAesHopeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-10 20:13
 **/
@Api(value = "用户",description = "用户管理")
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private HopeShiroRealm hopeShiroRealm;

    /**用户列表**/
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @RequiresPermissions("user:user:view")//默认查看权限开启
    @GetMapping("/user")
    public ModelAndView user(){
        return ResultHopeUtil.view("admin/user/user");
    }

    @RequiresPermissions("user:list")
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo list(UserConditionVo vo){
        PageInfo<SysUser> pageInfo=sysUserService.findPageBreakByCondition(vo);
        return ResultHopeUtil.tablePage(pageInfo);
    }

    /***
     * 添加用户
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @GetMapping("/add")
    public ModelAndView add(){
        return ResultHopeUtil.view("admin/user/add");
    }

    @RequiresPermissions("user:add")
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysUser sysUserFrom,String password2){
        SysUser user = sysUserService.getByUserName(sysUserFrom.getUsername());
        if (ObjectUtil.isNotNull(user)) {
            return ResultHopeUtil.error("该用户名[" + user.getUsername() + "]已存在！请更改用户名");
        }
        if (!sysUserFrom.getPassword().equals(password2)) {
            return ResultHopeUtil.error("两次密码不相同！");
        }
        try {
            sysUserFrom.setPassword(UsingAesHopeUtil.encrypt(sysUserFrom.getPassword(),sysUserFrom.getUsername()));
            sysUserFrom.setCreatetime(DateUtil.date());
            sysUserFrom.setUpdatetime(DateUtil.date());
            sysUserFrom.setUserId(RandomUtil.randomUUID().substring(0,7).toString());
            if (ObjectUtil.isNull(sysUserFrom.getStatus())){
                sysUserFrom.setStatus(SysUserStatusEnum.NORMAL.getCode());
            }
            if(!sysUserService.insert(sysUserFrom)){
                return ResultHopeUtil.error("用户添加失败！");
            }
            return ResultHopeUtil.success("用户添加成功！");
        }catch (Exception e){
            log.info("[用户添加失败]-[{}]",e.getMessage());
            return ResultHopeUtil.error("用户添加失败！");
        }
    }

    /***
     * 用户分配角色
     */
    @ApiOperation(value = "用户分配角色", notes = "用户分配角色")
    @PostMapping("/rolesWithSelected")
    @ResponseBody
    public ResponseVo<List<SysRole>> rolesWithSelected(Integer userId) {
        return ResultHopeUtil.success(null, sysRoleService.RoleListWithSelected(userId));
    }

    @RequiresPermissions("user:saveUserRoles")
    @PostMapping("/saveUserRoles")
    @ResponseBody
    public ResponseVo<List<SysRole>> saveUserRoles(Integer userId, String roleIds) {
        log.info("[用户id-[{}]，角色id-[{}]",userId,roleIds);
        //添加
        sysUserRoleService.addUserRole(userId,roleIds);
        //更新当前登录的用户的权限缓存
        List<String> userIds=new ArrayList<>();
        userIds.add(Convert.convert(String.class,userId));
        hopeShiroRealm.clearAuthorizationByUserId(userIds);
        return ResultHopeUtil.success("操作成功");
    }

    /***
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @RequiresPermissions("user:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public ResponseVo delete(@PathVariable("id") Integer id){
        if (sysUserService.deleteById(id)){
            return ResultHopeUtil.success("用户删除成功！");
        }else {
            return ResultHopeUtil.error("用户删除失败！");
        }
    }

    /***
     * 编辑用户
     */
    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id,ModelMap map){
        map.addAttribute("user",sysUserService.selectById(id));
        return ResultHopeUtil.view("admin/user/edit");
    }

    @RequiresPermissions("user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(SysUser sysUserFrom){
        int a=sysUserService.updateByUserId(sysUserFrom);
        if (a>0){
            return ResultHopeUtil.success("用户修改成功！");
        }else {
            return ResultHopeUtil.error("用户修改失败！");
        }
    }
}
