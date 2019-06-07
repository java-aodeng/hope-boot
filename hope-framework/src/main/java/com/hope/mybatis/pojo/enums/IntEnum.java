package com.hope.mybatis.pojo.enums;

/**
 * @program:hope-boot
 * @author:aodeng
 * @create:2018-10-15 13:24
 **/
public interface IntEnum<T> {
    /**
     * 枚举对应的整数值
     *
     * @return 值
     */
    int value();

    /**
     * 枚举类型描述
     *
     * @return 描述信息
     */
    String desc();
}
