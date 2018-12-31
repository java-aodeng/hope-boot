package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.object.ResponseVo;
import com.hope.service.SysResourceService;
import com.hope.shiro.service.ShiroService;
import com.hope.utils.ResultHopeUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    /***
     * 资源列表
     * @return
     */
    @RequiresPermissions("resources")
    @GetMapping("/resource")
    public ModelAndView resource(){
        return ResultHopeUtil.view("admin/resource/resource");
    }

    @RequiresPermissions("resource:list")
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

    @RequiresPermissions("resource:add")
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysResource sysResource){
        sysResource.setCreatetime(DateUtil.date());
        sysResource.setUpdatetime(DateUtil.date());
        sysResource.setResourceId(RandomUtil.randomUUID().substring(0,7).toString());
        if (sysResourceService.insert(sysResource)){
            shiroService.updatePermission();
            return ResultHopeUtil.success("添加资源成功");
        }else{
            return ResultHopeUtil.error("添加资源失败");
        }
    }

    /***
     * 根据资源id获取资源数据
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

    /***
     * 修改资源
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id,ModelMap map){
        map.put("resource",sysResourceService.selectResourceById(id));
        return ResultHopeUtil.view("admin/resource/edit");
    }

    @RequiresPermissions("resource:edit")
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(SysResource sysResource){
        sysResource.setUpdatetime(DateUtil.date());
        if (sysResourceService.updateById(sysResource)){
            shiroService.updatePermission();
            return ResultHopeUtil.success("修改资源成功");
        }else{
            return ResultHopeUtil.error("修改资源失败");
        }
    }

    /***
     * 删除资源
     * @param id
     * @return
     */
    @RequiresPermissions("resource:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public ResponseVo delete(@PathVariable("id") Integer id){
        if (id<=1017) {
            return ResultHopeUtil.error("系统资源，请不要删除！");
        }
        if (sysResourceService.selectSubPermsById(id)>0){
            return ResultHopeUtil.error("当前资源存在下级资源，无法删除！");
        }
        if (sysResourceService.deleteById(id)){
            return ResultHopeUtil.success("删除资源成功");
        }else{
            return ResultHopeUtil.error("删除资源失败");
        }
    }
}
