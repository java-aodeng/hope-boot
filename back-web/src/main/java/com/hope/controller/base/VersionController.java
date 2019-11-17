package com.hope.controller.base;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:aodeng(低调小熊猫)
 * @Description: TODO
 * @Date: 19-5-17
 **/
@RestController
@RequestMapping("/Version")
@Api(value = "版本",description = "版本")
public class VersionController {

    /**
    * @Description: 模块版本
    * @Author: aodeng
    * @Date: 19-5-17
    */
    @GetMapping("/v")
    public String version(){
        return "0.0.1-SNAPSHOT";
    }
}
