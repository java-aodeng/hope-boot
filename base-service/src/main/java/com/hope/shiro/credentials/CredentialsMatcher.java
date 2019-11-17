package com.hope.shiro.credentials;

import com.hope.utils.UsingAesHopeUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Shiro-匹配器
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(http://ilovey.live)
 * @微信公众号:低调小熊猫
 * @create:2018-10-21 13:28
 **/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    //重写父类的方法,用于验证密码有效性
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken intoken = (UsernamePasswordToken) token;
        //获取用户输入的密码
        String inPassword = new String(intoken.getPassword());
        //获得数据库的密码
        String dbPassword = (String) info.getCredentials();
        try {
            dbPassword = UsingAesHopeUtil.decrypt(dbPassword, intoken.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //验证密码是否一致
        return this.equals(dbPassword, inPassword);
    }
}
