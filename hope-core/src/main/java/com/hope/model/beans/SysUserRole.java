package com.hope.model.beans;

import com.hope.mybatis.pojo.CommonEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:04
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole {

    private static final long serialVersionUID = 2133462812918890038L;
    /**
     * 编号，主键，资源表
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     **/
    @Column(name = "userId")
    private String userId;
    /**
     * 角色id
     **/
    @Column(name = "roleId")
    private String roleId;

}