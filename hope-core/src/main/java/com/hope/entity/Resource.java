package com.hope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hope.beans.SysResource;

/**业务对象类
 * @program:hope-plus
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-16 11:18
 **/
public class Resource {
    /**---------------业务对象---------------**/
    private SysResource sysResource;

    public Resource() {
        this.sysResource = new SysResource();
    }

    public Resource(SysResource sysResource) {
        this.sysResource = sysResource;
    }
    @JsonIgnore
    public SysResource getSysResource() {
        return sysResource;
    }

    public void setSysResource(SysResource sysResource) {
        this.sysResource = sysResource;
    }
}
