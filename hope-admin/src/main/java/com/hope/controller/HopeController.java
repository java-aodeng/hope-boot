package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import com.google.code.kaptcha.Constants;
import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 17:13
 **/
@Api(value = "hope页面入口",description = "hope页面入口管理")
@Controller
public class HopeController {

    private static final Logger log= LoggerFactory.getLogger(HopeController.class);

    /***
     * 首页
     * @return
     */
    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = {"/","/common/index", "/index"})
    public String index(){
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

    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request,String username,String password,String verification,
                            @RequestParam(name = "rememberme",defaultValue = "false") Boolean rememberme){
        log.info("[进入登录方法....]");
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            log.info("[验证码通过]");
        } else {
           return ResultHopeUtil.error("验证码错误！");
        }
        UsernamePasswordToken token=new UsernamePasswordToken(username,password,rememberme);
        try {
            Subject subject= SecurityUtils.getSubject();
            //登录验证
            subject.login(token);
        }catch (Exception e){
            token.clear();
            log.info("[登录内部错误！请联系管理员检查！]-[{}]",DateUtil.date());
            return ResultHopeUtil.error(e.getMessage());
        }
        log.info("[登录成功]-[{}]",DateUtil.date());
        return ResultHopeUtil.success("登录成功！");
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping("/logout")
    public ModelAndView logout() {
        // http://www.oschina.net/question/99751_91561  此处有坑，这里其实可用使用shiro自带的退出，不用你实现任何东西
        log.info("[退出登录成功]-[{}]",DateUtil.date());
        return ResultHopeUtil.redirect("login");
    }

    /***
     * hope-plus
     * @param model
     * @return
     */
    @ApiOperation(value = "hope-plus", notes = "hope-plus")
    @RequestMapping("/hope-plus")
    public ModelAndView index_v1(Model model){
        return ResultHopeUtil.view("common/hope-plus");
    }

    /***
     * 测试
     * @param model
     * @return
     */
    @ApiOperation(value = "测试", notes = "测试")
    @RequestMapping("//onlineusers")
    public ModelAndView index_v2(Model model){
        log.info("[hope-index_v2-page]-[{}]","测试140");
        return ResultHopeUtil.view("admin/onlineusers/onlineuser");
    }
    /*
    @Autowired private RedisCacheManager redisCacheManager;
    @RequestMapping("/logout2")
    public ModelAndView logout2() {
        //自定义退出，清空缓存
        Subject subject=SecurityUtils.getSubject();
        if (ObjectUtil.isNotNull(subject)){
            String username=((SysUser)SecurityUtils.getSubject().getPrincipal()).getUsername();
            Serializable sessionId=SecurityUtils.getSubject().getSession().getId();
            Cache<String,Deque<Serializable>> cache=redisCacheManager.getCache(redisCacheManager.getKeyPrefix()+username);
            Deque<Serializable> deques=cache.get(username);
            for (Serializable deque:deques){
                if (sessionId.equals(deque)){
                    deques.remove(deque);
                    break;
                }
            }
            cache.put(username,deques);
        }
        subject.logout();
        log.info("[退出登录成功]-[{}]",DateUtil.date());
        return ResultHopeUtil.redirect("login");
    }*/
}