package com.hope.service;

import com.hope.model.beans.SysResource;
import com.hope.model.dto.Resource;
import com.hope.mybatis.service.BaseService;

import java.util.List;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysResourceService extends BaseService<SysResource> {
    /***
     * 测试
     * @return
     */
    List<SysResource> selectAlls();

    /***
     * 获取url和permission
     * @return
     */
    List<SysResource> listUrlAndPermission();

    /***
     *获取用户关联的所有资源
     * @return
     */
    List<SysResource> listResourcesByUserId();

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return set
     */
    Set<String> findPermsByUserId(String userId);
}
