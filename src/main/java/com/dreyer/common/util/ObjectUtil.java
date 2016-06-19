package com.dreyer.common.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @description
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:44:28
 * @version 1.0
 */
public class ObjectUtil {

	/**
	 * 将collection集合转为数组
	 * 
	 * @param collection
	 * @return
	 */
	public static Object[] toArray(Collection<Object> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new Object[collection.size()]);
	}

	/**
	 * 从数组中移除重复的元素
	 * 
	 * @param array
	 * @return
	 */
	public static Object[] removeDuplicate(Object[] array) {
		if (isEmpty(array)) {
			return null;
		}
		Set<Object> set = new LinkedHashSet<Object>();
		for (Object element : array) {
			set.add(element);
		}
		return toArray(set);
	}

	/**
	 * 从集合中移除重复的元素
	 * 
	 * @param collection
	 * @return
	 */
	public static Object[] removeDuplicate(Collection<Object> collection) {
		return removeDuplicate(collection.toArray());
	}

	/**
	 * 数组中是否包含某个元素
	 * 
	 * @param array
	 * @param element
	 * @return
	 */
	public static boolean containsElement(Object[] array, Object element) {
		if (array == null) {
			return false;
		}
		for (Object arrayEle : array) {
			if (nullSafeEquals(arrayEle, element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将元素数据添加至数组中
	 * 
	 * @param array
	 * @param obj
	 * @return
	 */
	public static <A, O extends A> A[] addObjectToArray(A[] array, O obj) {
		Class<?> compType = Object.class;
		if (array != null) {
			compType = array.getClass().getComponentType();
		} else if (obj != null) {
			compType = obj.getClass();
		}
		int newArrLength = (array != null ? array.length + 1 : 1);
		@SuppressWarnings("unchecked")
		A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
		if (array != null) {
			System.arraycopy(array, 0, newArr, 0, array.length);
		}
		newArr[newArr.length - 1] = obj;
		return newArr;
	}

	/**
	 * 判断Object对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {

		if (obj != null) {
			if (obj instanceof Object[]) {
				return ((Object[]) obj).length == 0;

			} else if (obj instanceof Collection) {
				return ((Collection) obj).size() == 0;

			} else if (obj instanceof Map) {
				return ((Map) obj).size() == 0;

			} else {
				if (obj.toString().trim().length() > 0) {
					return false;
				} else {
					return true;
				}
			}
		}
		return true;
	}

	/**
	 * 判断两个对象是否相等
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			if (o1 instanceof Object[] && o2 instanceof Object[]) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}
	
	/**
	 * 将Object转为int
	 * @param value
	 * @return
	 */
	public static int objectToInt(Object value) {
		if (!isEmpty(value)) {
			return Integer.parseInt(value.toString());
		}
		return 0;
	}

}
