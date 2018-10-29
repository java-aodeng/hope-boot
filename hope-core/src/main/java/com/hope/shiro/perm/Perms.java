package com.hope.shiro.perm;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**js调用 thymeleaf 实现按钮权限
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-29 13:59
 **/
@Component("perms")
public class Perms {
    public boolean hasPerm(String permission){
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}
