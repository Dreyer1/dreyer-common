package com.dreyer.common.util;

import java.util.*;

/**
 * @description 集合工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:44:36
 * @version 1.0
 */
public class CollectionUtil {
	
	/**
	 * 判断集合是否为空
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

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
	 * 数组转集合
	 * 
	 * @param objects
	 * @param allowRepeat
	 *            是否允许有重复的元素
	 * @return
	 */
	public static List<Object> arrayToList(Object[] objects, boolean allowRepeat) {
		if (ObjectUtil.isEmpty(objects)) {
			return null;
		}
		List<Object> list = new LinkedList<Object>();
		for (Object obj : objects) {
			if (allowRepeat) {
				list.add(obj);
			} else {
				if(!listContain(list, obj)) {
					list.add(obj);
				}
			}
		}
		return list;
	}

	/**
	 * 数组转集合，允许重复的元素
	 * 
	 * @param objects
	 * @return
	 */
	public static List<Object> arrayToList(Object[] objects) {
		return arrayToList(objects, false);
	}

	/**
	 * 将集合转为数组
	 * @param collection
	 * @return
	 */
	public static Object[] listToAry(Collection<Object> collection) {
		if (isEmpty(collection)) {
			return null;
		}
		return collection.toArray(new ObjectUtil[collection.size()]);

	}

	/**
	 * 将数组元素合并到集合中
	 * 
	 * @param array
	 * @param collection
	 */
	public static void mergeArrayIntoCollection(Object[] array,Collection<Object> collection) {
		if (isEmpty(collection)) {
			throw new IllegalArgumentException("Collection must not be null");
		}
		if (!ObjectUtil.isEmpty(array)) {
			for (Object obj : array) {
				collection.add(obj);
			}
		}
	}
	
	/**
     * 判断集合中是否包含某个元素，用于替换list.contains()方法，比contains()方法效率高很多！
     * 
     * @param list
     * @param value
     * @return
     */
    public static boolean listContain(List<?> list, Object value) {
        if (!isEmpty(list)) {
            Iterator<?> iterator = list.iterator();
            while (iterator.hasNext()) {
                Object temp = iterator.next();
                return value == null ? temp == null : temp.equals(value);
            }
        }
        return false;
    } 
    
    /**
     * 获取两个集合元素的交集
     * @param list1
     * @param list2
     * @return
     */
    public static List<?> getIntersection(List<?> list1,List<?> list2) {
    	if (!isEmpty(list1) && !isEmpty(list2)) {
    		//只在list1中保留list2中存在的元素
        	list1.retainAll(list2);
        	return list1;
    	}
    	return null;
    }


}
