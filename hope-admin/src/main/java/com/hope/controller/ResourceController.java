package com.hope.controller;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.object.PageResultVo;
import com.hope.service.SysResourceService;
import com.hope.utils.ResultHopeUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping("/resource")
    public ModelAndView resource(){
        return ResultHopeUtil.view("admin/resource/resource");
    }

    /***
     * 加载系统资源列表
     * @param sysResource
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public List<SysResource> list(SysResource sysResource){
        List<SysResource> resourceList=sysResourceService.selectResourceList(sysResource);
        //PageInfo<SysResource> list=new PageInfo<>(resourceList);
        return  resourceList;//ResultHopeUtil.tablePage(list);
    }

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
