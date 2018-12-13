package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import com.google.code.kaptcha.Constants;
import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
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
@Controller
public class HopeController {
    private static final Logger log= LoggerFactory.getLogger(HopeController.class);

    /*@Autowired
    private RedisCacheManager redisCacheManager;*/

    @GetMapping("/login")
    public String login() {
        return "common/login";
    }

    /*首页*/
    @GetMapping(value = {"/","/common/index", "/index"})
    public String index(){
        return "common/index";
    }

    /***
     * 登录
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseVo login(HttpServletRequest request,String username,String password,String verification,
                            @RequestParam(name = "rememberme",defaultValue = "false") Boolean rememberme){
        log.info("[进入登录方法....]");
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            //验证码通过
        } else {
           return ResultHopeUtil.error("验证码错误！");
        }
        UsernamePasswordToken token=new UsernamePasswordToken(username,password,rememberme);
        try {
            //验证
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);
        }catch (LockedAccountException e){
            token.clear();
            log.info("[用户已经被锁定不能登录，请联系管理员！]-[{}]",DateUtil.date());
            return ResultHopeUtil.error("用户已经被锁定不能登录，请联系管理员！");
        }catch (AuthenticationException e){
            token.clear();
            log.info("[用户名或者密码错误]-[{}]",DateUtil.date());
            return ResultHopeUtil.error("用户名或者密码错误！");
        }catch (Exception e){
            token.clear();
            log.info("[登录内部错误！请联系管理员检查！]-[{}]",DateUtil.date());
            return ResultHopeUtil.error("登录内部错误！请联系管理员检查！");
        }
        log.info("[登录成功]-[{}]",DateUtil.date());
//        return ResultHopeUtil.success("登录成功！");
        //此处不处理代为登录成功，有shiro代为处理
        return ResultHopeUtil.success("登录成功！");
    }

    @RequestMapping("/index_v1")
    public ModelAndView index_v1(Model model){
        return ResultHopeUtil.view("common/index_v1");
    }

    @RequestMapping("/index_v2")
    public ModelAndView index_v2(Model model){
        log.info("[hope-index_v2-page]-[{}]","测试140");
        return ResultHopeUtil.view("common/index_v2");
    }

    @RequestMapping("/error1")
    public ModelAndView error1(Model model){
        log.info("[hope-404-page]-[{}]","测试150");
        return ResultHopeUtil.view("common/error/404");
    }

/*    @GetMapping("/logout")
    public ModelAndView logout() {
        // http://www.oschina.net/question/99751_91561
        //自定义退出，清空缓存
        Subject subject=SecurityUtils.getSubject();
        if (null!=subject){
            String name=((SysUser)SecurityUtils.getSubject().getPrincipal()).getUsername();
            Serializable sessionId=SecurityUtils.getSubject().getSession().getId();
            Cache<String,Deque<Serializable>> cache=redisCacheManager.getCache(redisCacheManager.getKeyPrefix()+name);
            Deque<Serializable> deque=cache.get(name);
            for (Serializable deSerializable:deque){
                if (sessionId.equals(deque)){
                    deque.remove(deque);
                    break;
                }
            }
            cache.put(name,deque);
        }
        subject.logout();
        log.info("[退出登录成功]-[{}]",DateUtil.date());
        return ResultHopeUtil.redirect("index");
    }*/
}
