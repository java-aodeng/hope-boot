package com.hope.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hope.mapper.SysUserMapper;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.mybatis.service.impl.BaseServiceImpl;
import com.hope.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:21
 **/
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /***
     * 数据类型转换为业务类型
     * @param //sysUsers
     * @return
     */
/*    private List<User> getUsers(List<SysUser> sysUsers){
        if (CollectionUtils.isEmpty(sysUsers)){
            return null;
        }
        List<User> userList=new ArrayList<>();
        for(SysUser sysUser:sysUsers){
            userList.add(new User(sysUser));
        }
        return userList;
    }*/
    @Override
    public PageInfo<SysUser> findPageBreakByCondition(UserConditionVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        PageInfo list = new PageInfo<>(sysUsers);
        return list;
    }

    /***
     * 根据用户名查询
     * @param username
     * @return
     */
    @Override
    public SysUser getByUserName(String username) {
        SysUser sysuser = new SysUser();
        sysuser.setUsername(username);
        Assert.notNull(sysuser, "User不可为空！");
        SysUser sysUser = selectOne(sysuser);
        return null == sysUser ? null : sysUser;
    }

    @Override
    public List<SysUser> listUsersByRoleId(Integer roleId) {
        return sysUserMapper.listUsersByRoleId(roleId);
    }

    @Override
    public SysUser getByPrimaryKey(Integer integer) {
        return sysUserMapper.selectByPrimaryKey(integer);
    }

    @Override
    public void updateUserLastLoginInfo(SysUser sysuser) {
        sysUserMapper.updateLastLoginTime(sysuser);
    }

    @Override
    public SysUser selectUserByName(String userName) {
        return sysUserMapper.selectUserByName(userName);
    }

    @Override
    public List<SysUser> findByRoleId(Integer roleId) {
        return sysUserMapper.findByRoleId(roleId);
    }

    @Override
    public int updateByUserId(SysUser sysUser) {
        return sysUserMapper.updateByUserId(sysUser);
    }
}
