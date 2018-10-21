package com.hope.shiro.Credentials;

import com.hope.consts.CommonConst;
import com.hope.model.entity.User;
import com.hope.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Shiro-密码验证管理
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-21 13:19
 **/
public class ShiroPassWordCredentialsMatcher extends CredentialsMatcher{

    private static final Logger log= LoggerFactory.getLogger(ShiroPassWordCredentialsMatcher.class);

    /**用户登陆次数**/
    private static final String SHIRO_LOGIN_COUNT="shiro_login_count_";
    /**用户登陆是否被锁定**/
    private static final String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Integer userId=(Integer) info.getPrincipals().getPrimaryPrincipal();
        User user=sysUserService.getByPrimaryKey(userId);
        String username=user.getUsername();
        //访问计数
        ValueOperations<String,String> valueOperations=redisTemplate.opsForValue();
        String loginCountKey=SHIRO_LOGIN_COUNT+username;
        String isLockKey=SHIRO_IS_LOCK+username;
        valueOperations.increment(loginCountKey,1);
        //如果禁止登陆，提示
        if (redisTemplate.hasKey(isLockKey)){
            throw new ExcessiveAttemptsException("sorry，账号【"+username+"】已被禁止登录！");
        }
        //根据计数锁定用户
        String logcounts=String.valueOf(valueOperations.get(loginCountKey));
        int i=(5-Integer.parseInt(logcounts));
        //如果超过5次，就禁止一个小时
        if (i<=0){
            valueOperations.set(isLockKey,"LOCK");
            redisTemplate.expire(isLockKey,1, TimeUnit.HOURS);
            redisTemplate.expire(loginCountKey,1, TimeUnit.HOURS);
            throw new ExcessiveAttemptsException("sorr,密码输出错误次数太多，账号【"+username+"】已被禁止登录！");
        }

        //添加提示信息
        Boolean aBoolean=super.doCredentialsMatch(token,info);
        if (!aBoolean){
            String message=i<=0?"你的账号禁止1小时内登陆":"你还剩【"+i+"】次重试的机会！";
            throw new AccountException("sorry,账号或密码不正确！"+message);
        }

        //初始化登录计数
        redisTemplate.delete(loginCountKey);

        //更新用户最后一次登录状态
        try {
            sysUserService.updateUserLastLoginInfo(user);
        }catch (Exception e){
            e.printStackTrace();
        }

        //但验证全部通过之后，将用户信息存在session里面
        SecurityUtils.getSubject().getSession().setAttribute(CommonConst.USER_SESSION_KEY,user);
        return true;
    }
}
