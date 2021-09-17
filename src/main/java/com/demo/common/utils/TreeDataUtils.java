package com.demo.common.utils;


import com.demo.common.model.vo.Constants;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * element 树数据装载工具类
 */
@Slf4j
public class TreeDataUtils {

    /**
     * 泛型类String类型的属性值
     *
     * @param ?        要取值的泛型类
     * @param fileName String类型的属性名
     * @return
     */
    private static String TByValueToString(Object t, String fileName, Object object) {
        String fileValue = null;
        try {
            Class<?> tClass = t.getClass();

            //得到所有属性
            Field[] fields = tClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(fileName)) {
                    //将属性名字的首字母大写
                    String name = field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase());

                    if (object == null) {
                        //整合出 getId() 属性这个方法
                        Method m = tClass.getMethod("get" + name);
                        //调用这个整合出来的get方法，强转成自己需要的类型
                        fileValue = String.valueOf(m.invoke(t));
                    } else {
                        Method method = tClass.getMethod("set" + name, new Class[]{field.getType()});
                        method.invoke(t, object);
                    }
                }
            }
        } catch (Exception e) {
            log.info("没有这个属性");
        }
        return fileValue;
    }

    /**
     * 菜单树形数据装载
     *
     * @param list
     * @return
     */
    public static List<?> getSysTreeNodeDto(List<?> list, String tableIdName, String tablePIdName, String tableSetName) {
        List<?> parentList = list.stream().filter(e -> !StringUtil.isEmpty(TByValueToString(e, tablePIdName, null)) && (Constants.TREEPID.equals(TByValueToString(e, tablePIdName, null)) || StringUtil.isEmpty(TByValueToString(e, tablePIdName, null)) || TByValueToString(e, tableIdName, null).equals(TByValueToString(e, tablePIdName, null)))).collect(Collectors.toList());
        treeDataSetData(parentList, list, tableIdName, tablePIdName, tableSetName);
        return parentList;
    }

    public static void treeDataSetData(List<?> parentList, List<?> list, String tableIdName, String tablePIdName, String tableSetName) {
        parentList.stream().forEach(info -> {
            List<?> infoChildList = list.stream().filter(childInfo -> TByValueToString(info, tableIdName, null).equals(TByValueToString(childInfo, tablePIdName, null)) && !TByValueToString(info, tableIdName, null).equals(TByValueToString(childInfo, tableIdName, null))).collect(Collectors.toList());
            if (infoChildList != null && infoChildList.size() > 0) {
                treeDataSetData(infoChildList, list, tableIdName, tablePIdName, tableSetName);
                TByValueToString(info, tableSetName, infoChildList);
            }
        });
    }

}
