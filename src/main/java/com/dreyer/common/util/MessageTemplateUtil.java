package com.dreyer.common.util;

import com.dreyer.common.enums.MessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Dreyer
 * @date: 16/6/19 上午9:04
 * @description: 消息模板工具类
 */
public class MessageTemplateUtil {

    private static final Map<Integer, String> messageTemplate = new HashMap<Integer, String>();

    static {
        messageTemplate.put(MessageType.EMAIL.getValue(), "尊敬的用户,您好!您与{loginDate},在IP为:{IP}的机器上登录,如不是您本人的操作,请留意您的信息是否被盗取!");
    }

    /**
     * 根据消息类型,获取消息
     *
     * @param messageType
     * @return
     */
    public static String getMessageTemplate(int messageType) {
        return messageTemplate.get(messageType);
    }

}
