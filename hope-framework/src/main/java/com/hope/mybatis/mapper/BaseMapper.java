package com.hope.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 *
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-15 13:15
 **/
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper<T> {
}
