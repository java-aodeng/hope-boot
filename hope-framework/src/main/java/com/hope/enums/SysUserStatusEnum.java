package com.hope.enums;

/**
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-19 09:43
 **/
public enum SysUserStatusEnum {
    NORMAL(1, "正常"),
    DISABLE(2, "禁用"),;
    private Integer code;
    private String describe;

    SysUserStatusEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }

    public static SysUserStatusEnum get(Integer code) {
        if (null == code) {
            return NORMAL;
        }
        SysUserStatusEnum[] sysUserStatusEnums = SysUserStatusEnum.values();
        for (SysUserStatusEnum statusEnum : sysUserStatusEnums) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return NORMAL;
    }
}
