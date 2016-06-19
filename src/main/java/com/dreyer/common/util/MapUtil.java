package com.dreyer.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @description Map集合操作的工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:44:46
 * @version 1.0
 */
public class MapUtil {

	/**
	 * 判断Map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * JavaBean对象转为Map
	 *
	 * @param bean
	 *            要转换的JavaBean
	 * @return 转换后的Map对象
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Map bean2Map(Object bean) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		return bean2Map(bean, true);
	}

	/**
	 * JavaBean对象转为Map
	 *
	 * @param bean
	 *            要转换的JavaBean
	 * @param isAllowNull
	 *            JavaBean对象中的null值是否允许添加至Map中
	 * @return 转换后的Map对象
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map bean2Map(Object bean, boolean isAllowNull)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		if (bean == null) {
			return null;
		}
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (!isAllowNull) {
					if (result != null) {
						returnMap.put(propertyName, result);
					}
				} else {
					returnMap.put(propertyName, result);
				}
			}
		}
		return returnMap;
	}

	/**
	 * Map转为JavaBean对象
	 * 
	 * @param type
	 *            要转换的JavaBean对象类类型
	 * @param map
	 *            要转换的Map
	 * @return 转换之后的JavaBean对象
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Object map2Bean(Class type, Map map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		if (type == null) {
			throw new IllegalArgumentException("必须指定JavaBean的类类型！");
		}
		if (map == null || map.size() == 0) {
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	/**
	 * 从map中获取请求格式字符
	 * 
	 * @param map
	 * @return
	 */
	public static String getParamStrFromMap(Map<String, Object> map) {
		if (map == null || map.size() <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Set<String> set = map.keySet();
		Iterator<String> iterator = set.iterator();
		String key = "";
		Object value = "";
		while (iterator.hasNext()) {
			key = iterator.next();
			value = map.get(key);
			sb.append(key + "=" + value);
			sb.append("&");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}
	
	/**
	 * 将Properties属性文件元素合并至Map中
	 * 
	 * @param props
	 * @param map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void mergePropertiesIntoMap(Properties props, Map map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		}
		if (props != null) {
			for (Enumeration en = props.propertyNames(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				Object value = props.getProperty(key);
				if (value == null) {
					value = props.get(key);
				}
				map.put(key, value);
			}
		}
	}
	
	/**
	 * 从Map中获取对应类型的值（避免每次从Map中取值时都要强制类型转换）
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValueFromMap(Map<?, ?> map, Object key,Object defaultValue) {
		if (!ObjectUtil.isEmpty(map)) {
			Object result = map.get(key);
			return (T) ((T) result == null ? defaultValue : result);
		}
		return null;
	}
}
