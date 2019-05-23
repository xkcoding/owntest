package com.xkcoding.test.test27;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 测试反射调用方法返回固定格式内容
 * </p>
 *
 * @package: com.xkcoding.test.test27
 * @description: 测试反射调用方法返回固定格式内容
 * @author: yangkai.shen
 * @date: Created in 2019-05-23 10:16
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test27<T> {
    private Object dataObj;
    private Class dataCls;
    private String methodName;
    private Object[] params;
    private boolean isStatic;
    private Class[] paramTypes;

    @SuppressWarnings("unchecked")
    public Test27(Object dataObj, String methodName, Object... params) {
        this.methodName = methodName;
        this.params = params;
        if (dataObj instanceof Class) {
            this.dataObj = ReflectUtil.newInstance((Class) dataObj);
            this.dataCls = (Class) dataObj;
            this.isStatic = true;
        } else {
            this.dataObj = dataObj;
            this.dataCls = dataObj.getClass();
            this.isStatic = false;
        }
        if (ArrayUtil.isNotEmpty(params)) {
            paramTypes = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                paramTypes[i] = params[i].getClass();
            }
        }
    }

    public List<T> getList() {
        List<T> result;
        Method methodByName = ReflectUtil.getMethod(dataCls, methodName, paramTypes);

        if (isStatic) {
            result = ReflectUtil.invokeStatic(methodByName, params);
        } else {
            result = ReflectUtil.invoke(dataObj, methodByName, params);
        }

        return result;
    }

    public static void main(String[] args) {
        Test27<String> test27_1 = new Test27<>(Test.class, "list", "1");
        Test27<String> test27_2 = new Test27<>(new Test(), "listVirtual");
        Test27<String> test27_3 = new Test27<>(new Test(), "listVirtual", "4", "6");
        Test27<String> test27_4 = new Test27<>(new Test(), "listVirtual", 4, "6");

        System.out.println("test27_1 = " + test27_1.getList());
        System.out.println("test27_2 = " + test27_2.getList());
        System.out.println("test27_3 = " + test27_3.getList());
        System.out.println("test27_4 = " + test27_4.getList());
    }

    public static class Test {
        public static List<String> list(String start) {
            return Arrays.asList(start, "2", "3");
        }

        public List<String> listVirtual() {
            return Arrays.asList("4", "5", "6");
        }

        public List<String> listVirtual(String start, String end) {
            return Arrays.asList(start, "5", end);
        }

        public List<String> listVirtual(Integer start, String end) {
            return Arrays.asList(start + "", "5", end);
        }
    }
}
