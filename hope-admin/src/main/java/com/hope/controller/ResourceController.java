package com.hope.controller;

import com.hope.model.beans.SysRole;
import com.hope.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 加载角色资源列表树
     * @param sysRole
     * @return
     */
    @GetMapping("/roleResourceTreeData")
    @ResponseBody
    public List<Map<String,Object>> roleResourceTreeData(SysRole sysRole){
        List<Map<String,Object>> trees=sysResourceService.roleResourceTreeData(sysRole);
        return trees;
    }
}
