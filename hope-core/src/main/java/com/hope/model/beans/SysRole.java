package com.hope.model.beans;

import com.hope.mybatis.pojo.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * 角色数据对象类
 *
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色数据对象类")
public class SysRole extends CommonEntity {
    /**
     * 扩展的id
     **/
    @ApiModelProperty(value = "扩展的id", name = "roleId",required = true)
    @Column(name = "roleId")
    private String roleId;
    /**
     * 角色名称
     **/
    @ApiModelProperty(value = "角色名称", name = "role",required = true)
    private String role;
    /**
     * 角色描述
     **/
    @ApiModelProperty(value = "角色描述", name = "description")
    private String description;
    /**
     * 是否可用：1有效2删除
     **/
    @ApiModelProperty(value = "是否可用：1有效2删除", name = "status",required = true)
    private Integer status;
    /**
     * 是否选中
     **/
    @Transient
    private Integer selected;
}
