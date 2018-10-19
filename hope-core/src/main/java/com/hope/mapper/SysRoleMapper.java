package com.hope.mapper;

import com.hope.beans.SysRole;
import com.hope.entity.Role;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-16 13:59
 **/
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole>{
    List<SysRole> listRolesByUserId(Integer userId);
}
