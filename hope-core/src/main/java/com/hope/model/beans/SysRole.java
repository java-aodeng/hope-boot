package com.hope.model.beans;

import com.hope.mybatis.pojo.CommonEntity;
import lombok.*;

/**数据对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 10:51
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class SysRole extends CommonEntity{
    /**---------------数据对象---------------**/
    /**装逼的id**/
    private String roleUuid;
    /**角色名称**/
    private String role;
    /**角色描述**/
    private String description;
    /**是否可用：1有效2删除**/
    private String status;

}
