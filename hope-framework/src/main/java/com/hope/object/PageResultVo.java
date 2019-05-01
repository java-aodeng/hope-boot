package com.hope.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用于bootstrap table返回json格式
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 15:16
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResultVo {
    private Long total;
    private List rows;

    public PageResultVo(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResultVo() {
    }
}
