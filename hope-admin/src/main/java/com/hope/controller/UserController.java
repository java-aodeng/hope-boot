package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.enums.SysUserStatusEnum;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysUserService;
import com.hope.utils.ResultHopeUtil;
import com.hope.utils.UsingAesHopeUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

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

    /***
     * 添加用户
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(){
        return ResultHopeUtil.view("admin/user/add");
    }

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
}
