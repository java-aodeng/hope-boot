package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import com.google.code.kaptcha.Constants;
import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:hope-plus
 * @ClassName:LoginController
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-03-17 14:01
 * @Description: TODO
 * @Version 1.0
 **/
@Api(value = "登录", description = "登录管理api", position = 40, produces = "http")
@RestController
@Slf4j
public class LoginController {

    @ApiOperation(value = "后台登录", notes = "后台登录,200成功，500失败",produces="application/json, application/xml", consumes="application/json, application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "verification", value = "验证码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "rememberme", value = "记住登录", required = false, dataType = "Boolean", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @PostMapping("/login")
    public ResponseVo login(HttpServletRequest request, String username, String password, String verification,
                            @RequestParam(name = "rememberme", defaultValue = "false") Boolean rememberme) {
        log.info("[进入登录方法....]");
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            log.info("[验证码通过]");
        } else {
            return ResultHopeUtil.error("验证码错误！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberme);
        try {
            Subject subject = SecurityUtils.getSubject();
            //登录验证
            subject.login(token);
        } catch (Exception e) {
            token.clear();
            log.info("[登录内部错误！请联系管理员检查！]-[{}]", DateUtil.date());
            return ResultHopeUtil.error(e.getMessage());
        }
        log.info("[登录成功]-[{}]", DateUtil.date());
        return ResultHopeUtil.success("登录成功！");
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ApiOperation(value = "退出登录", notes = "退出登录，操作成功自动跳转login页面",produces="application/json, application/xml", consumes="application/json, application/xml")
    @GetMapping("/logout")
    public ModelAndView logout() {
        // http://www.oschina.net/question/99751_91561  此处有坑，这里其实可用使用shiro自带的退出，不用你实现任何东西
        log.info("[退出登录成功]-[{}]", DateUtil.date());
        return ResultHopeUtil.redirect("login");
    }
}
