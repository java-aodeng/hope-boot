package com.hope.service;

import com.hope.model.beans.SysResource;
import com.hope.model.beans.SysRole;
import com.hope.mybatis.service.BaseService;

import java.util.List;
import java.util.Map;
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
     * 根据用户id查询资源集合
     *
     * @param userId
     * @return set
     */
    Set<String> findPermsByUserId(Integer userId);

    /***
     * 获取角色资源
     * @param sysRole
     * @return
     */
    List<Map<String, Object>> roleResourceTreeData(SysRole sysRole);

    /***
     * 获取系统资源列表
     * @param sysResource
     * @return
     */
    List<SysResource> selectResourceList(SysResource sysResource);

    /***
     * 根据资源id查询数据
     * @param resourceId
     * @return
     */
    SysResource selectResourceById(Integer resourceId);

    /***
     * 获取所有资源数据
     * @return
     */
    List<Map<String, Object>> resourceTreeAll();

    /***
     * 根据id查看是否存在子级数据
     * @param resourceId
     * @return
     */
    int selectSubPermsById(Integer resourceId);
}
