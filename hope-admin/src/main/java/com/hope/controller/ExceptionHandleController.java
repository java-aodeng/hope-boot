package com.hope.controller;

import cn.hutool.core.util.ObjectUtil;
import com.hope.consts.CommonConst;
import com.hope.enums.ResponseStatusEnum;
import com.hope.exception.HopeException;
import com.hope.object.ResponseVo;
import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局统一异常处理
 *
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-18 15:46
 **/
@Api(value = "全局异常", description = "全局异常管理api", position = 60, produces = "http")
@RestControllerAdvice
public class ExceptionHandleController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandleController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseVo handleHope(Throwable e) {

        if (e instanceof HopeException) {
            log.error(e.getMessage());
            return ResultHopeUtil.error(e.getMessage());
        }

        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }

        ResponseStatusEnum statusEnum = ResponseStatusEnum.get(e.getMessage());

        if (ObjectUtil.isNotNull(statusEnum)) {
            log.error(statusEnum.getMessage());
            return ResultHopeUtil.error(statusEnum.getCode(), statusEnum.getMessage());
        }

        return ResultHopeUtil.error(CommonConst.DEFAULT_ERROR_CODE, ResponseStatusEnum.ERROR.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public String handleAuth(HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", ResponseStatusEnum.FORBIDDEN.getCode());
        return "forward:/error1";
    }
}