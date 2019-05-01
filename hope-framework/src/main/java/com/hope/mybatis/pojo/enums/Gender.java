package com.hope.mybatis.pojo.enums;

/**
 * @program:hope-boot
 * @author:aodeng
 * @create:2018-10-15 13:24
 **/
public enum Gender implements IntEnum<Gender> {
    MALE(1, "男"),
    FEMALE(2, "女"),
    ALL(3, "其他");
    private Integer value;
    private String desc;

    Gender(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public int value() {
        return this.value;
    }

    @Override
    public String desc() {
        return this.desc;
    }

    public static Gender of(int value) {
        for (Gender type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
