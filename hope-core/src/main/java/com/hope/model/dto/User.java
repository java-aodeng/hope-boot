package com.hope.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.model.beans.SysUser;
import com.hope.mybatis.pojo.CommonSerializable;
import com.hope.util.UsingAesHopeUtil;
import org.springframework.util.StringUtils;

import java.util.Date;

/**业务对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:19
 **/
public class User extends CommonSerializable{
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
                setPassword(password);
                /*setPassword(UsingAesHopeUtil.encrypt(password, loginname));*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysUser getSysUser() {
        return this.sysUser;
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
    public String getUserUuid() {
        return this.sysUser.getUserUuid();
    }

    public void setUserUuid(String userUuid) {
        this.sysUser.setUserUuid(userUuid);
    }

    public String getUsername() {
        return this.sysUser.getUsername();
    }


    public String getPassword() {
        return this.sysUser.getPassword();
    }


    public String getSalt() {
        return this.sysUser.getSalt();
    }

    public void setSalt(String salt) {
        this.sysUser.setSalt(salt);
    }

    public String getEmail() {
        return this.sysUser.getEmail();
    }

    public void setEmail(String email) {
        this.sysUser.setEmail(email);
    }

    public String getPhone() {
        return this.sysUser.getPhone();
    }

    public void setPhone(String phone) {
        this.sysUser.setPhone(phone);
    }

    public Integer getSex() {
        return this.sysUser.getSex();
    }

    public void setSex(Integer sex) {
        this.sysUser.setSex(sex);
    }

    public Integer getAge() {
        return this.sysUser.getAge();
    }

    public void setAge(Integer age) {
        this.sysUser.setAge(age);
    }

    public Integer getStatus() {
        return this.sysUser.getStatus();
    }

    public void setStatus(Integer status) {
        this.sysUser.setStatus(status);
    }

    public Date getLastLoginTime() {
        return this.sysUser.getLastLoginTime();
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.sysUser.setLastLoginTime(lastLoginTime);
    }

    public Integer getId() {
        return this.sysUser.getId();
    }

    public void setId(Integer id) {
        this.sysUser.setId(id);
    }

    public Date getCreatetime() {
        return this.sysUser.getCreatetime();
    }

    public void setCreatetime(Date createtime) {
        this.sysUser.setCreatetime(createtime);
    }

    public Date getUpdatetime() {
        return this.sysUser.getUpdatetime();
    }

    public void setUpdatetime(Date updatetime) {
        this.sysUser.setUpdatetime(updatetime);
    }
}
