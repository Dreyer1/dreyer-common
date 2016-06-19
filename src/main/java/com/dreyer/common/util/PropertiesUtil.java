package com.dreyer.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @description Properties文件工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午10:35:27
 * @version 1.0
 */
public class PropertiesUtil {
	
	/**
	 * 根据文件名获得properties配置
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Properties getProperties(String fileName) {
		Properties properties = new Properties();
		try {
			properties.load(PropertiesUtil.class.getClassLoader()
					.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 根据Properties和key获取value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties properties,String key) {
		String value = properties.getProperty(key);
		try {
			return switchPropertyValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据配置文件名和key获得value
	 * 
	 * @param fileName
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(String fileName, String key) {
		try {
			String value = getProperties(fileName).getProperty(key);
			return switchPropertyValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对值进行转码
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String switchPropertyValue(String value) throws Exception {
		if (value == null) {
			return value;
		}
		return new String(value.getBytes("ISO8859-1"), "UTF-8");
	}

}
