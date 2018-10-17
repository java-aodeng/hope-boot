package com.hope.mapper;

import com.hope.beans.SysUser;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-16 13:59
 **/
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser>{
}
