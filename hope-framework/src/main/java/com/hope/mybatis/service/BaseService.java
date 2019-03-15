package com.hope.mybatis.service;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用接口
 *
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-15 14:33
 **/
public interface BaseService<T> {
    /**
     * 新增一个实体，方法的实现需保证：当返回true时，实体entity的id属性已被赋值。
     *
     * @param entity 实体对象
     * @return 操作结果
     */
    boolean insert(T entity);

    /**
     * Null 字段使用数据库默认值
     *
     * @param entity 实体对象
     * @return 操作结果
     */
    boolean insertSelective(T entity);

    /**
     * 批量添加
     *
     * @param recordList 批量添加
     * @return 添加结果
     */
    boolean insertList(List<T> recordList);

    /**
     * 根据主键删除一个实体
     *
     * @param key 主键
     * @return 操作结果
     */
    boolean deleteById(Object key);

    /**
     * 根据主键字段进行查询
     *
     * @param key 主键
     * @return 操作结果
     */
    T selectById(Object key);

    /**
     * 根据主键更新实体全部字段(TODO 公共属性部分不必赋值，入库拦截前会自动将修改人 修改时间加上)
     *
     * @param entity 实体对象
     * @return 操作结果
     * @author Levin
     */
    boolean updateById(T entity);

    /**
     * 根据主键更新不为NUll的值
     *
     * @param entity 实体对象
     * @return 操作结果
     * @author Levin
     */
    boolean updateSelectiveById(T entity);

    /**
     * 查询所有
     *
     * @return 操作结果
     */
    List<T> listAll();

    /**
     * 用实体作为条件对象进行查询
     *
     * @param record 实体对象
     * @return 查询结果
     */
    List<T> select(T record);

    /**
     * 查询单条记录
     *
     * @param record 实体对象
     * @return 查询结果
     */
    T selectOne(T record);

    /**
     * 查询单条记录
     *
     * @param example 对象
     * @return 查询结果
     */
    T findByExample(Example example);


}
