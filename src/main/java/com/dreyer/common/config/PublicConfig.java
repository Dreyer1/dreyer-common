package com.dreyer.common.config;

import com.dreyer.common.util.ResourceUtils;

import java.util.Map;

/**
 * @author: Dreyer
 * @date: 16/6/15 下午11:23
 * @description 公共配置类
 */
public class PublicConfig {
    /**
     * 系统文件配置 加载。
     */
    public static Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("public_system").getMap();

    /**
     * 通知URL
     */
    public final static String NOTIFY_RECEIVE_URL = PUBLIC_SYSTEM.get("notify_receive_url");

    /**
     * 通知的最大次数
     */
    public final static String NOTIFY_LIMIT = PUBLIC_SYSTEM.get("notify_limit");


}
