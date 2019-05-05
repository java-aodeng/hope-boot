package com.hope.interceptor;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @program:hope-boot
 * @ClassName:TTOInterceptor
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @create:2019-04-12 13:24
 * @Description: TTO
 * @Version 1.0
 **/
public class TTOInterceptor implements HandlerInterceptor {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TTOInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
                                Exception exception) throws Exception {
        // 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，也就是在DispatcherServlet
        // 渲染了对应的视图之后执行。用于进行资源清理。

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
                           ModelAndView modelAndView) throws Exception {
        // 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，可以在这个方法中对Controller
        // 处理之后的ModelAndView 对象进行操作。

        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        if (object instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("execution time: ").append(executeTime).append("ms").append("\n");
            LOGGER.info(sb.toString());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，
        // 当返回值为true 时就会继续调用下一个Interceptor的preHandle
        // 方法，如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法；

        // 请求日志输出
        actionLog(request, response, object);

        return true;
    }

    /**
     * 输出请求日志
     *
     * @param request
     * @param response
     * @param object
     */
    private void actionLog(HttpServletRequest request, HttpServletResponse response, Object object) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        LOGGER.info("URI	: " + request.getRequestURI().toString());

        try {
            HandlerMethod handlerMethod = (HandlerMethod) object;
            String controller = handlerMethod.getBean().getClass().getName().toString();
            LOGGER.info("Controller : " + controller + ".(" + controller.substring(controller.lastIndexOf(".") + 1)
                    + ".java:1)");
            LOGGER.info("Method	: " + handlerMethod.getMethod().getName());
        } catch (Exception e) {
            LOGGER.info("Controller : ");
            LOGGER.info("Method	: ");
        }

        LOGGER.info("Params	: " + getParamString(request.getParameterMap()));

    }

    /**
     * 日志输出参数
     *
     * @param map
     * @return
     */
    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append("\t");
            } else {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }
}

@ControllerAdvice
class ResponseBodyAdviceImp implements ResponseBodyAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(TTOInterceptor.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2, Class arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
        LOGGER.info("Result : " + body.toString());
        return body;
    }

}