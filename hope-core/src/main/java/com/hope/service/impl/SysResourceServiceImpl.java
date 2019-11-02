package com.hope.service.impl;

import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.model.dto.Resource;
import com.hope.mapper.SysResourceMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    private final SysResourceMapper sysResourceMapper;

    public SysResourceServiceImpl(SysResourceMapper sysResourceMapper){
        this.sysResourceMapper=sysResourceMapper;
    }

    /***
     * 数据类型转换
     * @param sysResources
     * @return
     */
    private List<Resource> getResources(List<SysResource> sysResources) {
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Resource> resourceList = new ArrayList<>();
        for (SysResource sysResource : sysResources) {
            resourceList.add(new Resource(sysResource));
        }
        return resourceList;
    }

    @Override
    public List<SysResource> selectAlls() {
        return sysResourceMapper.selectAlls();
    }

    @Override
    public List<SysResource> listUrlAndPermission() {
        return sysResourceMapper.listUrlAndPermission();
    }

    @Override
    public List<SysResource> listResourcesByUserId() {
        return sysResourceMapper.listResourcesByUserId();
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return sysResourceMapper.findPermsByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> roleResourceTreeData(SysRole sysRole) {
        Integer roleId = sysRole.getId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysResource> resourceList = sysResourceMapper.selectAlls();
        if (null != roleId) {
            //根据角色id查询
            List<String> roleResourceList = sysResourceMapper.selectResourceTree(roleId);
            trees = getTrees(resourceList, true, roleResourceList, true);
        } else {
            trees = getTrees(resourceList, false, null, true);
        }
        return trees;
    }

    /***
     *
     * @param resourceList 菜单列表
     * @param isCheck 是否选中
     * @param roleResourceList 角色已存在菜单列表
     * @param permsFlag 是否显示权限标识符
     * @return
     */
    public List<Map<String, Object>> getTrees(List<SysResource> resourceList, boolean isCheck, List<String> roleResourceList, boolean permsFlag) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysResource resource : resourceList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", resource.getId());
            map.put("pId", resource.getParentId());
            map.put("name", MenuName(resource, roleResourceList, permsFlag));
            map.put("title", resource.getName());
            if (isCheck) {
                map.put("checked", roleResourceList.contains(resource.getId() + resource.getPermission()));
            } else {
                map.put("checked", false);
            }
            trees.add(map);
        }
        return trees;
    }

    /***
     * 菜单名称
     * @param resource
     * @param roleMenuList
     * @param permsFlag
     * @return
     */
    public String MenuName(SysResource resource, List<String> roleMenuList, boolean permsFlag) {
        StringBuilder sb = new StringBuilder();
        sb.append(resource.getName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + resource.getPermission() + "</font>");
        }
        return sb.toString();
    }

    @Override
    public List<SysResource> selectResourceList(SysResource sysResource) {
        return sysResourceMapper.selectResourceList(sysResource);
    }

    @Override
    public SysResource selectResourceById(Integer resourceId) {
        return sysResourceMapper.selectResourceById(resourceId);
    }

    @Override
    public List<Map<String, Object>> resourceTreeAll() {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysResource> resourceList = sysResourceMapper.selectAll();
        trees = getTrees(resourceList, false, null, false);
        return trees;
    }

    @Override
    public int selectSubPermsById(Integer resourceId) {
        return sysResourceMapper.selectSubPermsById(resourceId);
    }

}
