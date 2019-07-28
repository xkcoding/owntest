package com.xkcoding.test.test33;

import java.util.function.Consumer;

/**
 * <p>
 * 测试 JDK 8 函数式接口：{@link Consumer}
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-07-29 15:58
 */
public class Test33 {
    public static void main(String[] args) {
        // Consumer 方法泛型 是传入参数类型
        // Consumer 方法原生写法
        Consumer<Integer> func1 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("integer = " + integer);
            }
        };
        // Consumer 方法lambda写法
        Consumer<Integer> func2 = integer -> System.out.println("integer = " + integer);

        func1.accept(1);
    }
}
