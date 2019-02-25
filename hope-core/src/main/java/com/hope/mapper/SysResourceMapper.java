package com.hope.mapper;

import com.hope.model.beans.SysResource;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-16 13:58
 **/
@Mapper
@Repository
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResource> selectResourceList(SysResource sysResource);

    List<SysResource> selectAlls();

    List<SysResource> listUrlAndPermission();

    List<SysResource> listResourcesByUserId();

    /**
     * 根据用户id查询资源集合
     *
     * @param userId 状态
     * @return set
     */
    Set<String> findPermsByUserId(Integer userId);

    /***
     * 根据角色id查询资源
     * @param roleId
     * @return
     */
    List<String> selectResourceTree(Integer roleId);

    SysResource selectResourceById(Integer resourceId);

    int selectSubPermsById(Integer resourceId);
}
