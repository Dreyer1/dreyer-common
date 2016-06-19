package com.dreyer.common.enums;

/**
 * @author: Dreyer
 * @date: 16/3/19 下午10:23
 * @description 针对 是/否 两种状态的枚举
 */
public enum PublicEnum {

    YES("是"),

    NO("否");

    /**
     * 描述
     */
    private String desc;

    PublicEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * 根据枚举名称获取枚举类型
     *
     * @param name
     * @return
     */
    public static PublicEnum getPublicEnum(String name) {
        for (PublicEnum publicEnum : values()) {
            if (publicEnum.name().equalsIgnoreCase(name)) {
                return publicEnum;
            }
        }
        return null;
    }

}
