package com.demo.common.utils;

import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * 字符串处理工具类
 * @author :jky
 * @Description:
 * @ Date: Created in 2021-04-27 11:23
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param tagetStr 目标字符串 "null" "undefined" 都认为是空
     * @return 如果字符串是空，怎返回true,否则返回false
     * @author zoboy
     * @version v 1.0
     */
    public static boolean isEmpty(String tagetStr){
        if(tagetStr == null || "".equals(tagetStr) || "null".equals(tagetStr) || "undefined".equals(tagetStr)){
            return true;
        }
        return false;
    }
    
    /**
	 * 返回空字符串
	 * "null" "undefined" 都认为是空
	 * @param str
	 * @return
	 * 
	 */
	public static String isNull(Object str) {
		if (isEmpty(String.valueOf(str))) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	/**
	 * 判断val是否为空
	 * @param value
	 * @return boolean
	 */
	public static boolean isNotNull(Object value) {
		return !isValNull(value);
	}

	public static boolean isValNull(Object value) {
		if (value == null)
			return true;
		if ((value instanceof StringBuffer)
				&& (((StringBuffer) value).length() <= 0))
			return true;
		if ((value instanceof String)
				&& ("null".equalsIgnoreCase((String) value) || ((String) value)
				.trim().length() == 0))
			return true;
		if ((value instanceof Integer) && (((Integer) value).intValue() == 0))
			return true;
		if ((value instanceof Object[]) && (((Object[]) value).length <= 0))
			return true;
		if ((value instanceof Collection) && ((Collection) value).size() <= 0)
			return true;
		if ((value instanceof Dictionary) && ((Dictionary) value).size() <= 0)
			return true;
		if ((value instanceof HashMap) && (((HashMap) value).size() <= 0))
			return true;
		if ("null".equalsIgnoreCase(value.toString())) {
			return true;
		}
		return false;
	}
}
