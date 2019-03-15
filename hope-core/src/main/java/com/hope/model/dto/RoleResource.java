package com.hope.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.model.beans.SysRoleResource;

import java.util.Date;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:18
 **/
public class RoleResource {
    /**
     * ---------------业务对象---------------
     **/
    private SysRoleResource sysRoleResource;

    public RoleResource() {
        this.sysRoleResource = new SysRoleResource();
    }

    public RoleResource(SysRoleResource sysRoleResource) {
        this.sysRoleResource = sysRoleResource;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysRoleResource getSysRoleResource() {
        return this.sysRoleResource;
    }

    public String getRoleId() {
        return this.sysRoleResource.getRoleId();
    }

    public void setRoleId(String roleId) {
        this.sysRoleResource.setRoleId(roleId);
    }

    public String getResourceId() {
        return this.sysRoleResource.getResourceId();
    }

    public void setResourceId(String resourceId) {
        this.sysRoleResource.setResourceId(resourceId);
    }

    public Integer getId() {
        return this.sysRoleResource.getId();
    }

    public void setId(Integer id) {
        this.sysRoleResource.setId(id);
    }

}
