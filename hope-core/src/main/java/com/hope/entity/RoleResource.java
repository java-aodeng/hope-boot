package com.hope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.beans.SysRoleResource;

/**业务对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:18
 **/
public class RoleResource {
    /**---------------业务对象---------------**/
    private SysRoleResource sysRoleResource;

    public RoleResource() {
         this.sysRoleResource=new SysRoleResource();
    }
    public RoleResource(SysRoleResource sysRoleResource){
        this.sysRoleResource=sysRoleResource;
    }

    @JsonIgnore
    public SysRoleResource getSysRoleResource() {
        return sysRoleResource;
    }

    public void setSysRoleResource(SysRoleResource sysRoleResource) {
        this.sysRoleResource = sysRoleResource;
    }
}
