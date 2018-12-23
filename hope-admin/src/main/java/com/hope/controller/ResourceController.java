package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.object.PageResultVo;
import com.hope.object.ResponseVo;
import com.hope.service.SysResourceService;
import com.hope.shiro.service.ShiroService;
import com.hope.utils.ResultHopeUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private ShiroService shiroService;

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
        return  resourceList;
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

    /***
     * 新增资源
     * @return
     */
    @GetMapping("/add/{id}")
    public ModelAndView add(@PathVariable("id") Integer id,ModelMap map){
        SysResource resource=null;
        if(id>0){
            resource=sysResourceService.selectResourceById(id);
        }else {
            resource=new SysResource();
            resource.setId(0);
            resource.setName("主目录");
        }
        map.put("resource",resource);
        return ResultHopeUtil.view("admin/resource/add");
    }

    /***
     * 保存新增资源
     * @param sysResource
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysResource sysResource){
        sysResource.setCreatetime(DateUtil.date());
        sysResource.setResourceId(RandomUtil.randomUUID().substring(0,7).toString());
        if (sysResourceService.insert(sysResource)){
            shiroService.updatePermission();
            return ResultHopeUtil.success("添加资源成功");
        }else{
            return ResultHopeUtil.error("添加资源失败");
        }
    }
    /***
     * 根据id获取资源数据
     * @return
     */
    @GetMapping("/selectResourceById/{resourceId}")
    public ModelAndView selectResourceById(@PathVariable("resourceId") Integer resourceId, ModelMap map){
        map.put("resource",sysResourceService.selectResourceById(resourceId));
        return ResultHopeUtil.view("admin/resource/tree");
    }

    /***
     * 获取资源数据
     * @return
     */
    @GetMapping("/resourceTreeAll")
    @ResponseBody
    public List<Map<String,Object>> resourceTreeAll(){
        List<Map<String,Object>> trees=sysResourceService.resourceTreeAll();
        return trees;
    }
}
