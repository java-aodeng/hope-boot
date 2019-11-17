package com.hope.model.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户数据对象类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:58
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户数据对象类")
public class SysUser implements Serializable {
    private static final long serialVersionUID = -4080167041530353373L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id", name = "id",required = true)
    private Integer id;
    /**
     * 扩展的id
     **/
    @ApiModelProperty(value = "扩展的id", name = "userId",required = true)
    @Column(name = "userId")
    private String userId;
    /**
     * 用户名
     **/
    @ApiModelProperty(value = "用户名", name = "username",required = true)
    private String username;
    /**
     * 密码
     **/
    @ApiModelProperty(value = "密码", name = "password",required = true)
    private String password;
    /**
     * 盐
     **/
    @ApiModelProperty(value = "盐", name = "salt")
    private String salt;
    /**
     * 邮箱
     **/
    @ApiModelProperty(value = "邮箱", name = "email")
    private String email;
    /**
     * 联系方式
     **/
    @ApiModelProperty(value = "联系方式", name = "phone")
    private String phone;
    /**
     * 性别：1男2女3未知
     **/
    @ApiModelProperty(value = "性别：1男2女3未知", name = "sex")
    private Integer sex;
    /**
     * 年龄
     **/
    @ApiModelProperty(value = "年龄", name = "age")
    private Integer age;
    /**
     * 用户状态：1有效2删除
     **/
    @ApiModelProperty(value = "用户状态：1有效2删除", name = "status",required = true)
    private Integer status;
    /**
     * 最后登陆时间
     **/
    @ApiModelProperty(value = "最后登陆时间", name = "lastLoginTime")
    private Date lastLoginTime;
    /**
     * 创建时间
     **/
    @ApiModelProperty(value = "创建时间", name = "createtime")
    @Column(name = "create_time")
    private Date createtime;
    /**
     * 修改时间
     **/
    @ApiModelProperty(value = "修改时间", name = "updatetime")
    @Column(name = "update_time")
    private Date updatetime;
}