package com.xkcoding.test.test39;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * 测试 Guava Lists.partition
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/11/26 16:21
 */
public class Test39 {
    private static final Integer TOTAL_NUMBER = 2000;

    private static final Integer PART_NUM = 156;

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < TOTAL_NUMBER; i++) {
            list.add(new Random().nextInt(100));
        }

        List<List<Integer>> partition = Lists.partition(list, PART_NUM);
        partition.forEach(v -> {
            System.out.println("size: " + v.size());
            System.out.println("data: " + v);
            System.out.println("============");
        });
    }
}
