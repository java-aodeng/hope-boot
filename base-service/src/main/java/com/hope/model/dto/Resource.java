package com.hope.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.model.beans.SysResource;

import java.util.Date;

/**
 * 资源业务对象类
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:18
 **/
public class Resource {
    /**
     * ---------------业务对象---------------
     **/
    private SysResource sysResource;

    public Resource() {
        this.sysResource = new SysResource();
    }

    public Resource(SysResource sysResource) {
        this.sysResource = sysResource;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysResource getSysResource() {
        return this.sysResource;
    }

    public String getUresourceUuid() {
        return this.sysResource.getResourceId();
    }

    public void setUresourceUuid(String uresourceUuid) {
        this.sysResource.setResourceId(uresourceUuid);
    }

    public String getName() {
        return this.sysResource.getName();
    }

    public void setName(String name) {
        this.sysResource.setName(name);
    }

    public String getDescription() {
        return this.sysResource.getDescription();
    }

    public void setDescription(String description) {
        this.sysResource.setDescription(description);
    }

    public String getUrl() {
        return this.sysResource.getUrl();
    }

    public void setUrl(String url) {
        this.sysResource.setUrl(url);
    }

    public Integer getType() {
        return this.sysResource.getType();
    }

    public void setType(Integer type) {
        this.sysResource.setType(type);
    }

    public Integer getPriority() {
        return this.sysResource.getPriority();
    }

    public void setPriority(Integer priority) {
        this.sysResource.setPriority(priority);
    }

    public Integer getParentId() {
        return this.sysResource.getParentId();
    }

    public void setParentId(Integer parentId) {
        this.sysResource.setParentId(parentId);
    }

    public String getPermission() {
        return this.sysResource.getPermission();
    }

    public void setPermission(String permission) {
        this.sysResource.setPermission(permission);
    }

    public String getIcon() {
        return this.sysResource.getIcon();
    }

    public void setIcon(String icon) {
        this.sysResource.setIcon(icon);
    }

    public Integer getStatus() {
        return this.sysResource.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysResource.setStatus(status);
    }

    public Integer getId() {
        return this.sysResource.getId();
    }

    public void setId(Integer id) {
        this.sysResource.setId(id);
    }

    public Date getCreatetime() {
        return this.sysResource.getCreatetime();
    }

    public void setCreatetime(Date createtime) {
        this.sysResource.setCreatetime(createtime);
    }

    public Date getUpdatetime() {
        return this.sysResource.getUpdatetime();
    }

    public void setUpdatetime(Date updatetime) {
        this.sysResource.setUpdatetime(updatetime);
    }
}
