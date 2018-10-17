package com.hope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.beans.SysRole;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:18
 **/
public class Role {
    /**---------------业务对象---------------**/
    private SysRole sysRole;

    public Role(){
        this.sysRole = new SysRole();
    }

    public Role(SysRole sysRole){
        this.sysRole = sysRole;
    }
    @JsonIgnore
    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }
}
