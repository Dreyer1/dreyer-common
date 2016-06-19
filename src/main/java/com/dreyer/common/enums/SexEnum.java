package com.dreyer.common.enums;

/**
 * @author: Dreyer
 * @date: 16/3/20 上午12:37
 * @description 性别枚举类
 */
public enum SexEnum {

    MAN("男"),

    WOMAN("女");

    /**
     * 描述信息
     */
    private String desc;

    SexEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据性别名称获取枚举类型
     *
     * @param name
     * @return
     */
    public static SexEnum getSexEnum(String name) {
        for (SexEnum sexEnum : values()) {
            if (sexEnum.name().equalsIgnoreCase(name)) {
                return sexEnum;
            }
        }
        return null;
    }
}
