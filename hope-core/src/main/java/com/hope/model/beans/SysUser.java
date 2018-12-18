package com.hope.model.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**用户数据对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:58
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser  implements Serializable {
    public SysUser() {
    }

    public SysUser(String userId, String username, String password, String salt, String email, String phone, Integer sex, Integer age, Integer status, Date lastLoginTime, Date createtime, Date updatetime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    private static final long serialVersionUID = -4080167041530353373L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**装逼的id**/
    @Column(name="userId")
    private String userId;
    /**用户名**/
    private String username;
    /**密码**/
    private String password;
    /**盐**/
    private String salt;
    /**邮箱**/
    private String email;
    /**联系方式**/
    private String phone;
    /**性别：1男2女3未知**/
    private Integer sex;
    /**年龄**/
    private Integer age;
    /**用户状态：1有效2删除**/
    private Integer status;
    /**最后登陆时间**/
    private Date lastLoginTime;
    /**创建时间**/
    @Column(name = "create_time")
    private Date createtime;
    /**修改时间**/
    @Column(name = "update_time")
    private Date updatetime;
    /**
     *
     * 重写获取盐值方法，自定义realm使用
     * Gets credentials salt.
     *
     * @return the credentials salt
     */
    public String getCredentialsSalt() {
        return username + "nbclass.com" + salt;
    }
}