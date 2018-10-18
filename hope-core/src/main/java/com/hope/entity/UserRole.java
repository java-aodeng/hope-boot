package com.hope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.beans.SysUserRole;

/**业务对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:19
 **/
public class UserRole {
    /**---------------业务对象---------------**/
    private SysUserRole sysUserRole;

    public UserRole(){
        this.sysUserRole=new SysUserRole();
    }
    public UserRole(SysUserRole sysUserRole){
        this.sysUserRole=sysUserRole;
    }

    @JsonIgnore
    public SysUserRole getSysUserRole() {
        return sysUserRole;
    }

    public void setSysUserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }
}
