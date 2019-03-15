package com.hope.model.vo;

import com.hope.model.beans.SysResource;
import com.hope.object.BaseConditionVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-25 10:41
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVo extends BaseConditionVo {
    private SysResource resource;
    private String type;
}
