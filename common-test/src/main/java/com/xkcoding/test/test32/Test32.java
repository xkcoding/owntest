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

    static Integer testFunc(Integer src, Function<Integer, Integer> function) {
        return function.apply(src);
    }

    public static void main(String[] args) {
        // Function方法泛型有2个，一个是传入参数类型，一个是返回值参数类型
        // Function方法原生写法
        Function<Integer, String> func1 = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "this num is: " + integer;
            }
        };
        // 基本用法，apply，接收一个参数，返回一个结果
        String result1 = func1.apply(1);
        System.out.println("result1 = " + result1);

        // Function方法lambda写法
        Function<String, String> func2 = str -> "this input is:「" + str + "」";

        // 高阶用法，andThen，参数是接下来需要执行的 Function 方法，返回值仍然是一个 Function 方法
        // andThen，先执行 当前方法的 apply，再执行 after 方法的 apply
        Function<Integer, String> func3 = func1.andThen(func2);
        String result2 = func3.apply(2);
        System.out.println("result2 = " + result2);

        // 高阶用法，compose，参数是预先需要执行的 Function 方法，返回值仍然是一个 Function 方法
        // compose，先执行 before 方法的 apply，再执行当前方法的 apply
        Function<Integer, String> func4 = func2.compose(func1);
        String result3 = func4.apply(3);
        System.out.println("result3 = " + result3);

        Integer result4 = testFunc(5, i -> i + 1);
        System.out.println("result4 = " + result4);
    }
}
