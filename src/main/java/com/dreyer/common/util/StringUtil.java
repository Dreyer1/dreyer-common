package com.dreyer.common.util;

import java.util.Map;
import java.util.UUID;

/**
 * @description 字符串工具类
 * @author Dreyer
 * @email hy.dreyer@qq.com
 * @date 2015年9月7日 下午8:44:17
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		return (str == null) || (str.trim().length() == 0);
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {

		return (str != null) && (str.trim().length() > 0);
	}


	/**
	 * 判断多个字符串对象中是否存在空值
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean hasEmpty(String... strs) {
		if (strs == null || strs.length == 0) {
			return true;
		}
		for (String s : strs) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断多个字符串是否全部为空
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean isAllEmpty(String... strs) {
		if (ObjectUtil.isEmpty(strs)) {
			return true;
		}

		for (String o : strs) {
			if (!isEmpty(o)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检测是否为空，返回处理后的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String checkNull(String str) {
		String tempStr = "";
		if (str == null || str.trim().length() == 0) {
			return tempStr;
		} else {
			return str.trim();
		}

	}

	/**
	 * 判断字符串是否有值，包括空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isHasValue(String str) {

		return (str != null) && (str.length() > 0);
	}

	/**
	 * 判断字符串中是否包含空格
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainsSpace(String str) {

		return (str != null) && (str.indexOf(" ") != -1);
	}

	/**
	 * 判断字符串是否为true，不区分大小写
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isTrue(String str) {

		return "true".equalsIgnoreCase(str);
	}

	/**
	 * 空字符串转换 if str == null return defaultStr
	 * 
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String nvlStr(String str, String defaultStr) {

		return str == null ? defaultStr : str;
	}

	/**
	 * 字符串转换 if str == null return ""
	 * 
	 * @param str
	 * @return
	 */
	public static String nvlStr(String str) {

		return nvlStr(str, "");
	}


	/**
	 * 去除字符串中的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String cutSpace(String str) {
		if (isEmpty(str)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}
	
	/**
     * 判断字符串是否在字符串数组中
     * @param str
     * @param ary
     * @return
     */
    public static int indexofAry (String str,String[] ary) {
        if (ObjectUtil.isEmpty(ary)) {
            return -1;
        }
        for (int i=0;i<ary.length;i++) {
            if (ary[i] != null && ary[i].trim().equals(str.trim())) {
                return i;
            }
        }
        return -1;
    }
    
	/**
	 * 把字符串中的占位字符替换为真实的字符串值 str = nickName={nickName}&password={password} map =
	 * map.put("nickName", "Dreyer");map.put("password", "123"); result =
	 * nickName=Dreyer&password=123
	 * 
	 * @param str
	 *            字符串
	 * @param map
	 *            字符串值集合
	 * @return
	 */
	public static String replaceWithRealVal(String str, Map<String, Object> map) {

		String result = str;
		String s;

		for (String key : map.keySet()) {
			s = "{" + key + "}";
			result = result.replace(s, map.get(key).toString());
		}
		return result;
	}

	/**
	 * 判断一串字符中是否已某个前缀开头（不区分大小写）
	 * 
	 * @param str
	 *            字符串
	 * @param prefix
	 *            前缀
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 判断一串字符是否已某个后缀结尾
	 * 
	 * @param str
	 *            字符串
	 * @param suffix
	 *            后缀
	 * @return
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length())
				.toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 统计指定字符串中出现指定字符的次数
	 * 
	 * @param str
	 *            指定字符串
	 * @param sub
	 *            指定字符
	 * @return
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0
				|| sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 字符串替换
	 * 
	 * @param inString
	 *            要替换的字符串
	 * @param oldPattern
	 *            要替换的旧字符
	 * @param newPattern
	 *            要替换为的新字符
	 * @return
	 */
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (!isHasValue(inString) || !isHasValue(oldPattern)
				|| newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0;
		int index = inString.indexOf(oldPattern);

		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));

		return sb.toString();
	}

	/**
	 * 从指定字符串中删除指定字符（将指定字符替换为空字符）
	 * 
	 * @param inString
	 * @param pattern
	 * @return
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 从指定字符串中删除任意的指定字符（只要出现的都会被去除）
	 * 
	 * @param inString
	 * @param charsToDelete
	 * @return
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!isHasValue(inString) || !isHasValue(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 获取从前台传入的textare的格式值（按照前台在textarea的输入格式在后台输出）
	 * 
	 * @param str
	 * @return
	 */
	public static String withTextareaFormat(String str) {
		// 下面的代码将字符串以正确方式显示（包括回车，换行，空格）
		while (str.indexOf("<") != -1) {
			str = str.substring(0, str.indexOf("<")) + "&lt;"
					+ str.substring(str.indexOf("<") + 1);
		}
		while (str.indexOf(">") != -1) {
			str = str.substring(0, str.indexOf(">")) + "&gt;"
					+ str.substring(str.indexOf(">") + 1);
		}
		while (str.indexOf("\n") != -1) {
			str = str.substring(0, str.indexOf("\n")) + "<br>"
					+ str.substring(str.indexOf("\n") + 1);
		}
		while (str.indexOf(" ") != -1) {
			str = str.substring(0, str.indexOf(" ")) + "&nbsp;"
					+ str.substring(str.indexOf(" ") + 1);
		}
		return str;
	}

	/**
	 * 获取UUID
	 *
	 * @return UUID
	 */
	public static String getUUID() {

		return (UUID.randomUUID() + "").replaceAll("-", "");
	}

}
