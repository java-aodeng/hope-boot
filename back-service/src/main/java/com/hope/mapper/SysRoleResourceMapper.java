package com.hope.mapper;

import com.hope.model.beans.SysRoleResource;
import com.hope.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 13:59
 **/
@Mapper
@Repository
public interface SysRoleResourceMapper extends BaseMapper<SysRoleResource> {
}
