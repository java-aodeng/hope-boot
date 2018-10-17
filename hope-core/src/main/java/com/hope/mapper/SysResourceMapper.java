package com.hope.mapper;

import com.hope.beans.SysResource;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-16 13:58
 **/
@Mapper
@Repository
public interface SysResourceMapper extends BaseMapper<SysResource>{
    List<SysResource> selectAlls();

    List<SysResource> listUrlAndPermission();
}
