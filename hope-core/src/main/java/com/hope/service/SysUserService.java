package com.hope.service;

import com.github.pagehelper.PageInfo;
import com.hope.model.beans.SysUser;
import com.hope.model.vo.UserConditionVo;
import com.hope.mybatis.service.BaseService;

import java.util.List;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 15:10
 **/
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 分页查询，使用分页插件
     *
     * @param vo
     * @return
     */
    PageInfo<SysUser> findPageBreakByCondition(UserConditionVo vo);

    /***
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUser getByUserName(String username);

    /***
     * 根据角色id查询用户列表
     * @return
     */
    List<SysUser> listUsersByRoleId(Integer roleId);

    /***
     * 根据主键查询
     * @param integer
     * @return
     */
    SysUser getByPrimaryKey(Integer integer);

    /***
     * 更新用户的最后一次的登录状态
     * @param sysUser
     * @return
     */
    void updateUserLastLoginInfo(SysUser sysUser);

    SysUser selectUserByName(String userName);

    /***
     * 根据角色id查询用户数据
     * @param roleId
     * @return
     */
    List<SysUser> findByRoleId(Integer roleId);

    /***
     * 根据id更新数据
     * @param sysUser
     * @return
     */
    int updateByUserId(SysUser sysUser);
}
