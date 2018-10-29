package com.hope.model.beans;

import com.hope.mybatis.pojo.CommonEntity;
import lombok.*;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResource extends CommonEntity{
    /**角色id**/
    private String roleId;
    /**资源id**/
    private String resourceId;

}