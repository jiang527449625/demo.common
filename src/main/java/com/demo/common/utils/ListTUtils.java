package com.demo.common.utils;

import java.util.*;

/**
 * list工具类
 */
public class ListTUtils {
 
	/**
     * 删除重复元素,保持顺序
     * @param list
     */
	public static <T> void removeDuplicateWithOrder(List<T> list) {
        Set<T> set = new HashSet<T>();
        List<T> newList = new ArrayList<T>();
        for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
            T element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
    }

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection)
    {
        return collection == null || collection.isEmpty();
    }

}
