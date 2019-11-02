package com.hope.model.beans;

import com.hope.mybatis.pojo.CommonEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResource {
    private static final long serialVersionUID = 2702192486029856480L;
    /**
     * 编号，主键，资源表
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色id
     **/
    @Column(name = "roleId")
    private String roleId;
    /**
     * 资源id
     **/
    @Column(name = "resourceId")
    private String resourceId;

}