package com.hope.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.object.ResponseVo;
import com.hope.service.SysResourceService;
import com.hope.shiro.service.ShiroService;
import com.hope.utils.ResultHopeUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-12-03 22:22
 **/
@Api(value = "资源", description = "资源管理api", position = 10, produces = "http")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    private final SysResourceService sysResourceService;
    private final ShiroService shiroService;

    public ResourceController(SysResourceService sysResourceService,ShiroService shiroService){
        this.sysResourceService=sysResourceService;
        this.shiroService=shiroService;
    }
    
    /** 
    * @Description: 资源列表
    * @Param: [sysResource]
    * @return: [sysResource]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "资源列表", notes = "资源列表",position = 11,produces="application/json, application/xml", consumes="application/json, application/xml",response = SysResource.class)
    @RequiresPermissions("resource:list")
    @RequestMapping("/list")
    public List<SysResource> list(SysResource sysResource) {
        List<SysResource> resourceList = sysResourceService.selectResourceList(sysResource);
        return resourceList;
    }

    /** 
    * @Description: 加载角色资源列表树
    * @Param: [sysRole]
    * @return: [sysRole]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "加载角色资源列表树", notes = "加载角色资源列表树",produces="application/json, application/xml", consumes="application/json, application/xml",response = List.class)
    @RequestMapping("/roleResourceTreeData")
    public List<Map<String, Object>> roleResourceTreeData(SysRole sysRole) {
        List<Map<String, Object>> trees = sysResourceService.roleResourceTreeData(sysRole);
        return trees;
    }

    /** 
    * @Description: 打开新增资源
    * @Param: [id, map]
    * @return: [id, map]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "打开新增资源", notes = "打开新增资源")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "资源主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/add/{id}")
    public ModelAndView add(@PathVariable("id") Integer id, ModelMap map) {
        SysResource resource = null;
        if (id > 0) {
            resource = sysResourceService.selectResourceById(id);
        } else {
            resource = new SysResource();
            resource.setId(0);
            resource.setName("主目录");
        }
        map.put("resource", resource);
        return ResultHopeUtil.view("admin/resource/add");
    }

    /** 
    * @Description: 保存新增资源
    * @Param: [sysResource]
    * @return: [sysResource]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "保存新增资源", notes = "保存新增资源")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("resource:add")
    @PostMapping("/add")
    public ResponseVo add(SysResource sysResource) {
        sysResource.setCreatetime(DateUtil.date());
        sysResource.setUpdatetime(DateUtil.date());
        sysResource.setResourceId(RandomUtil.randomUUID().substring(0, 7).toString());
        if (sysResourceService.insert(sysResource)) {
            shiroService.updatePermission();
            return ResultHopeUtil.success("添加资源成功");
        } else {
            return ResultHopeUtil.error("添加资源失败");
        }
    }

    /** 
    * @Description: 根据资源id获取资源数据
    * @Param: [resourceId, map]
    * @return: [resourceId, map]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "根据资源id获取资源数据", notes = "根据资源id获取资源数据")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "resourceId", value = "资源主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/selectResourceById/{resourceId}")
    public ModelAndView selectResourceById(@PathVariable("resourceId") Integer resourceId, ModelMap map) {
        map.put("resource", sysResourceService.selectResourceById(resourceId));
        return ResultHopeUtil.view("admin/resource/tree");
    }

    /** 
    * @Description: 加载资源Tree数据
    * @Param: []
    * @return: []
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "加载资源Tree数据", notes = "加载资源Tree数据，返回List<Map<String, Object>>的数据")
    @GetMapping("/resourceTreeAll")
    public List<Map<String, Object>> resourceTreeAll() {
        List<Map<String, Object>> trees = sysResourceService.resourceTreeAll();
        return trees;
    }

    /** 
    * @Description: 打开修改资源
    * @Param: [id, map]
    * @return: [id, map]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "打开修改资源", notes = "打开修改资源")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "资源主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id, ModelMap map) {
        map.put("resource", sysResourceService.selectResourceById(id));
        return ResultHopeUtil.view("admin/resource/edit");
    }

    /** 
    * @Description: 保存修改资源
    * @Param: [sysResource]
    * @return: [sysResource]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "保存修改资源", notes = "保存修改资源")
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("resource:edit")
    @PostMapping("/edit")
    public ResponseVo edit(SysResource sysResource) {
        sysResource.setUpdatetime(DateUtil.date());
        if (sysResourceService.updateById(sysResource)) {
            shiroService.updatePermission();
            return ResultHopeUtil.success("修改资源成功");
        } else {
            return ResultHopeUtil.error("修改资源失败");
        }
    }

    /** 
    * @Description: 删除资源根据id
    * @Param: [id]
    * @return: [id]
    * @Author: aodeng
    * @Date: 19-3-17
    */ 
    @ApiOperation(value = "删除资源根据id", notes = "删除资源根据id")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "resourceId", value = "资源主键id", required = true, dataType = "Integer", paramType = "query")
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "操作失败，返回错误原因"),
    })
    @RequiresPermissions("resource:delete")
    @PostMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable("id") Integer id) {
        if (id <= 1017) {
            return ResultHopeUtil.error("系统资源，请不要删除！");
        }
        if (sysResourceService.selectSubPermsById(id) > 0) {
            return ResultHopeUtil.error("当前资源存在下级资源，无法删除！");
        }
        if (sysResourceService.deleteById(id)) {
            return ResultHopeUtil.success("删除资源成功");
        } else {
            return ResultHopeUtil.error("删除资源失败");
        }
    }
}
