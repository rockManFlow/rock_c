package com.kuark.tool.base.excel;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class ListToArrayUtil {

    /**
     * 获取对象属性，返回一个字符串数组
     * 
     * @param o 对象
     * @return String[] 字符串数组
     */
    private static String[] getFiledName(Object o) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            System.out.println("获取对象属性异常:");
        }
        return null;
    }

    /**
     * 使用反射根据属性名称获取t属性的get方法
     * 
     * @param o 操作对象
     * @return List<Method> get方法
     */

    private static List<Method> getGetField(String[] fieldNames, Object o) {
        List<Method> methods = new ArrayList<Method>();
        for (String fieldName : fieldNames) {
        	if(fieldName.indexOf("TITLE")>0){
        		continue;
        	}
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);

            if (getter.contains("$")) {
                System.out.println("contains $:{}"+getter);
                continue;
            }
            Method method = null;
            try {
                method = o.getClass().getMethod(getter, new Class[] {});
            } catch (NoSuchMethodException e) {
                System.out.println("NoSuchMethodException:{}");
                continue;
            }
            methods.add(method);
        }
        return methods;

    }

    /**
     * 将list集合转换为二维string数组
     * 
     * @param list 要转换的集合
     * @return String[][] 返回的sting数组
     */

    public static String[][] listToArrayWay(List<?> list) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Object o = list.get(0);

        String[] filedNames = getFiledName(o);
        int filedNum = filedNames.length;
        int listSize = list.size();
        List<Method> methods = getGetField(filedNames, o);

        String[][] arrs = new String[listSize][filedNum];
        int i = 0;
        for (Object object : list) {
            int j = 0;
            for (Method method : methods) {
                Object value = null;
                try {
                    value = method.invoke(object, new Object[] {});
                } catch (Exception e) {
                    System.out.println("属性不存在");
                }
                if (value == null) {
                    arrs[i][j] = "";
                } else if (value instanceof String) {
                    arrs[i][j] = (String) value;
                } else if (value instanceof Date) {
                    arrs[i][j] = dateFormat.format((Date) value);
                } else {
                    arrs[i][j] = value.toString();
                }
                j++;
            }
            i++;
        }
        return arrs;
    }

}
