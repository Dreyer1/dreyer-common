package com.dreyer.common.enums;

/**
 * @author: Dreyer
 * @date: 16/6/19 上午9:04
 * @description: 消息类型
 */
public enum MessageType {
    /**
     * 邮件消息
     */
    EMAIL(1);

    private Integer value;

    MessageType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
