package com.hope.service.impl;

import com.hope.mapper.SysResourceMapper;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-03 22:30
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Override
    public List<Map<String, Object>> roleMenuTreeData(SysRole sysRole) {
        Integer roleId=sysRole.getId();
        List<Map<String,Object>> trees=new ArrayList<Map<String, Object>>();
        List<SysResource> resourceList=sysResourceMapper.selectAlls();
        if(null != roleId){
            //根据角色id查询
            //List<String>
        }else {
            //碎觉
            //trees=
        }
        return null;
    }

    /***
     *
     * @param resourceList 菜单列表
     * @param isCheck 是否选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否显示权限标识符
     * @return
     */
    public List<Map<String,Object>>  getTrees(List<SysResource> resourceList,boolean isCheck,List<String> roleMenuList,boolean permsFlag){
        List<Map<String,Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysResource resource:resourceList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", resource.getId());
            map.put("pId", resource.getParentId());
            map.put("name", MenuName(resource, roleMenuList, permsFlag));
            if (isCheck){
                map.put("checked",roleMenuList.contains(resource.getId() + resource.getPermission()));
            }else{
                map.put("checked",false);
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
    public String MenuName(SysResource resource,List<String> roleMenuList,boolean permsFlag){
        StringBuilder sb = new StringBuilder();
        sb.append(resource.getName());
        if(permsFlag){
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + resource.getPermission() + "</font>");
        }
        return sb.toString();
    }
}