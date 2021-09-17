package com.demo.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author :jky
 * @Description:
 * @ Date: Created in 2021-04-27 15:05
 */
public class SortUtils {

    public static StringBuffer formatUrlParam(Map<String, String> param) {
        StringBuffer sb = new StringBuffer();
        try {
            List<String> list = getCode(param);
            for (String item : list) {
                if (!StringUtil.isEmpty(item)) {
                    sb.append(item + "=" + param.get(item));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sb;
    }

    public static List getCode(Map<String, String> param) {
        List<String> list = new ArrayList();
        Iterator iter = param.entrySet().iterator();  //获得map的Iterator
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            list.add(entry.getKey().toString());
        }
        Collections.sort(list);
        return list;
    }

}
