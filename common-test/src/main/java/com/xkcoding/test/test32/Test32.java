package com.xkcoding.test.test32;

import java.util.function.Function;

/**
 * <p>
 * 测试 JDK 8 函数式接口：{@link Function}
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-07-29 13:53
 */
public class Test32 {
    public static void main(String[] args) {
        // Function方法泛型有2个，一个是传入参数类型，一个是返回值参数类型
        // 原生写法
        Function<Integer, String> func1 = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "this num is: " + integer;
            }
        };
        String result1 = func1.apply(1);
        System.out.println("result1 = " + result1);

        // lambda写法
        Function<String, String> func2 = str -> "this input is:「" + str + "」";

        Function<Integer, String> func3 = func1.andThen(func2);
        String result2 = func3.apply(2);
        System.out.println("result2 = " + result2);

        Function<Integer, String> func4 = func2.compose(func1);
        String result3 = func4.apply(3);
        System.out.println("result3 = " + result3);
    }
}
