package com.xkcoding.test.test34;

import java.util.function.Predicate;

/**
 * <p>
 * 测试 JDK 8 函数式接口：{@link Predicate}
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-07-29 16:08
 */
public class Test34 {
    private static boolean checkNumber(Integer number, Predicate<Integer> predicate) {
        return predicate.test(number);
    }

    public static void main(String[] args) {
        Integer number = 84;
        // 无论如何都是true
        boolean result1 = checkNumber(number, test -> true);
        System.out.println("result1 = " + result1);

        // 是否偶数
        boolean result2 = checkNumber(number, test -> test % 2 == 0);
        System.out.println("result2 = " + result2);

        // 是否奇数
        boolean result3 = checkNumber(number, test -> test % 2 == 1);
        System.out.println("result3 = " + result3);
    }
}
