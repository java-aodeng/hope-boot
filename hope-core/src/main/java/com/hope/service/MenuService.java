package com.hope.service;

import com.hope.model.beans.SysRole;

import java.util.List;
import java.util.Map;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-12-03 22:30
 **/
public interface MenuService{
    List<Map<String,Object>> roleMenuTreeData(SysRole sysRole);
}
