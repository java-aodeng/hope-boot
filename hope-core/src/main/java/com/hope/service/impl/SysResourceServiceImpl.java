package com.hope.service.impl;

import com.hope.beans.SysResource;
import com.hope.mapper.SysResourceMapper;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public List<SysResource> selectAlls() {
        return sysResourceMapper.selectAlls();
    }

    @Override
    public List<SysResource> listUrlAndPermission() {
        return sysResourceMapper.listUrlAndPermission();
    }
}
