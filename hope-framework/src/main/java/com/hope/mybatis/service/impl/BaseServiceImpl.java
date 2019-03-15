package com.hope.mybatis.service.impl;

import com.hope.mybatis.mapper.BaseMapper;
import com.hope.mybatis.service.BaseService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用实现类
 *
 * @program:hope-plus
 * @author:aodeng
 * @create:2018-10-15 14:42
 **/
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired(required = false)
    private BaseMapper<T> baseMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(T entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertSelective(T entity) {
        return baseMapper.insertSelective(entity) > 0;
    }

    @Override
    public boolean insertList(List<T> recordList) {
        return baseMapper.insertList(recordList) > 0;
    }

    @Override
    public boolean deleteById(Object key) {
        return baseMapper.deleteByPrimaryKey(key) > 0;
    }

    @Override
    public T selectById(Object key) {
        return baseMapper.selectByPrimaryKey(key);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return baseMapper.updateByPrimaryKey(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSelectiveById(T entity) {
        return baseMapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public List<T> listAll() {
        return this.baseMapper.selectAll();
    }

    @Override
    public List<T> select(T record) {
        return this.baseMapper.select(record);
    }

    @Override
    public T selectOne(T record) {
        return this.baseMapper.selectOne(record);
    }

    @Override
    public T findByExample(Example example) {
        final List<T> records = this.baseMapper.selectByExample(example);
        if (records == null) {
            return null;
        } else if (records.size() > 1) {
            throw new TooManyResultsException();
        }
        return null;
    }
}
