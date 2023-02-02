package com.kuark.tool.advance.advance20201111.lamda;

import org.apache.commons.lang3.StringUtils;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author rock
 * @detail
 * @date 2021/1/25 15:24
 */
public class FunctionHandler {
    public static <T> String getFieldName(MyFunction<T> func) {
        try {
            //解析lambda表达式
            Method method = func.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            //java会帮我们序列化成SerializedLambda 对象，包含了函数式接口和实现方法的信息
            SerializedLambda serializedLambda = (SerializedLambda)method.invoke(func);
            String getter = serializedLambda.getImplMethodName();
            return resolveFieldName(getter);
        } catch (ReflectiveOperationException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static String resolveFieldName(String getMethodName) {
        if (getMethodName.startsWith("get")) {
            getMethodName = getMethodName.substring(3);
        } else if (getMethodName.startsWith("is")) {
            getMethodName = getMethodName.substring(2);
        }

        return firstToLowerCase(getMethodName);
    }

    private static String firstToLowerCase(String param) {
        return StringUtils.isBlank(param) ? "" : param.substring(0, 1).toLowerCase() + param.substring(1);
    }
}
