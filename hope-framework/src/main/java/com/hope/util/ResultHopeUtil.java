package com.hope.util;

import com.hope.consts.CommonConst;
import com.hope.enums.ResponseStatusEnum;
import com.hope.vo.ResponseVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**接口返回工具
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 14:05
 **/
public class ResultHopeUtil {
    /**ModelAndView**/
    public static ModelAndView view(String view){
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String,Object> model){
        return new ModelAndView(view,model);
    }

    public static ModelAndView redirect(String view){
        return new ModelAndView("redirect"+view);
    }

    /**vo**/
    public static ResponseVo vo(int code,String message,Object data){
        return new ResponseVo(code,message,data);
    }


    /**error**/
    public static ResponseVo error(int code,String message){
        return vo(code,message,null);
    }

    public static ResponseVo error(ResponseStatusEnum statusEnum){
        return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
    }

    public static ResponseVo error(String message,Object data){
        return vo(CommonConst.DEFAULT_ERROR_CODE,message,null);
    }

    /**success**/
    public static ResponseVo success(String message,Object data){
        return vo(CommonConst.DEFAULT_SUCCESS_CODE,message,data);
    }

    public static ResponseVo success(String message){
        return success(message,null);
    }

    public static ResponseVo success(ResponseStatusEnum statusEnum){
        return vo(statusEnum.getCode(),statusEnum.getMessage(),null);
    }
}
