package com.hope.service.impl;

import com.hope.mapper.SysResourceMapper;
import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
}
