package com.hope.service;

import com.hope.beans.SysResource;
import com.hope.entity.Resource;
import com.hope.mybatis.service.BaseService;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysResourceService extends BaseService<Resource> {
    /***
     * 测试
     * @return
     */
    List<Resource> selectAlls();

    /***
     * 获取url和permission
     * @return
     */
    List<Resource> listUrlAndPermission();

    /***
     *获取用户关联的所有资源
     * @return
     */
    List<Resource> listResourcesByUserId();
}
