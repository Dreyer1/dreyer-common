package com.dreyer.common.util;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @description Jackson json工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月3日 下午3:38:53
 * @version 1.0
 */
public class JacksonUtil {

	/**
	 * 空的json字符
	 */
	private static final String JSON_EMPTY = "{}";

	/**
	 * ObjectMapper 提供单例供全局使用
	 */
	private static class SingletonHolder {
		private static ObjectMapper mapper;
		static {
			mapper = new ObjectMapper();
			// 设置日期的格式
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.CHINESE));
			// 序列化对象时，不包括空的字段
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			// 忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(
					DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
					true);
		}
	}
	/**
	 * 获取ObjectMapper单例对象
	 * @return
	 */
	public static ObjectMapper getMapper() {
		return SingletonHolder.mapper;
	}

	/**
	 * Object转json
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		try {
			return getMapper().writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON_EMPTY;
	}

	/**
	 * json字符转Object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object jsonToObject(String json, Class<?> clazz) {
		try {
			return getMapper().readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON_EMPTY;
	}

	/**
	 * json字符串转Collection集合，支持泛型 目前List、Set、Map已测试通过
	 * 为Map类型时，elementClass可变参数为key.Class,value.Class
	 * 
	 * @param json
	 * @param listClass
	 * @param elementClass
	 * @return
	 */
	public static Object jsonToCollection(String json, Class<?> listClass,
			Class<?>... elementClass) {
		try {
			return getMapper().readValue(json,
					getCollectionType(listClass, elementClass));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON_EMPTY;
	}

	/**
	 * 对泛型进行支持
	 * 
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return getMapper().getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
	}

	/**
	 * 根据json字符获取json节点
	 * 
	 * @param json
	 * @return
	 */
	public static JsonNode getJsonNodeValue(String json) {
		try {
			return getMapper().readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据json字符和指定的key值，获取对应的value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static JsonNode getJsonValue(String json, String key) {
		JsonNode node = getJsonNodeValue(json);
		return node.get(key);
	}
}
