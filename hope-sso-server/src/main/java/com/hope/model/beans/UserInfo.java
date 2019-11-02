package com.hope.model.beans;

/**
 * @program:hope-boot
 * @ClassName:UserInfo
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @create:2019-04-01 13:08
 * @Description: TODO
 * @Version 1.0
 **/
public class UserInfo {

    private int userid;
    private String username;
    private String password;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
