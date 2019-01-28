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
     * Gets the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id
     * <p>You can use getId() to get the value of id</p>
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the value of userId
     *
     * @return the value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the userId
     * <p>You can use getUserId() to get the value of userId</p>
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * <p>You can use getUsername() to get the value of username</p>
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * <p>You can use getPassword() to get the value of password</p>
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the value of salt
     *
     * @return the value of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt
     * <p>You can use getSalt() to get the value of salt</p>
     *
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Gets the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email
     * <p>You can use getEmail() to get the value of email</p>
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone
     * <p>You can use getPhone() to get the value of phone</p>
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the value of sex
     *
     * @return the value of sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * Sets the sex
     * <p>You can use getSex() to get the value of sex</p>
     *
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * Gets the value of age
     *
     * @return the value of age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the age
     * <p>You can use getAge() to get the value of age</p>
     *
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets the value of status
     *
     * @return the value of status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status
     * <p>You can use getStatus() to get the value of status</p>
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the value of lastLoginTime
     *
     * @return the value of lastLoginTime
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * Sets the lastLoginTime
     * <p>You can use getLastLoginTime() to get the value of lastLoginTime</p>
     *
     * @param lastLoginTime
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * Gets the value of createtime
     *
     * @return the value of createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * Sets the createtime
     * <p>You can use getCreatetime() to get the value of createtime</p>
     *
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * Gets the value of updatetime
     *
     * @return the value of updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * Sets the updatetime
     * <p>You can use getUpdatetime() to get the value of updatetime</p>
     *
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}