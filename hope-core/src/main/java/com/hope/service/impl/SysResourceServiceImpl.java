package com.hope.service.impl;

import com.hope.model.beans.SysResource;
import com.hope.model.dto.Resource;
import com.hope.mapper.SysResourceMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService{

    @Autowired
    SysResourceMapper sysResourceMapper;

    /***
     * 数据类型转换
     * @param sysResources
     * @return
     */
    private List<Resource> getResources(List<SysResource> sysResources){
        if (CollectionUtils.isEmpty(sysResources)){
            return null;
        }
        List<Resource> resourceList=new ArrayList<>();
        for (SysResource sysResource:sysResources){
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
    public Set<String> findPermsByUserId(String userId) {
        return sysResourceMapper.findPermsByUserId(userId);
    }
}
