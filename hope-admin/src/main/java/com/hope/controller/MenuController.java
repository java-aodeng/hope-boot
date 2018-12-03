package com.hope.controller;

import com.hope.model.beans.SysRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-03 22:22
 **/
@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String,Object>> roleMenuTreeData(SysRole sysRole){
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        return list;
    }
}
