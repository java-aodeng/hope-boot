package com.hope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.beans.SysUser;
import org.springframework.util.StringUtils;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:19
 **/
public class User {
    /**---------------业务对象---------------**/
    private SysUser sysUser;

    public User(){
        this.sysUser=new SysUser();
    }
    public User(SysUser sysUser){
        this.sysUser=sysUser;
    }

    public User(String loginname, String password) {
        this();
        setUsername(loginname);
        if (!StringUtils.isEmpty(password)) {
            try {
                //setPassword(PasswordUtil.encrypt(password, loginname));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @JsonIgnore
    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public void setUsername(String username) {
        this.sysUser.setUsername(username);
    }

    public void setPassword(String password) {
        this.sysUser.setPassword(password);
    }
}
