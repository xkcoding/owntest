package com.xkcoding.test.test19;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 列表字段乘积求和
 * </p>
 *
 * @package: com.xkcoding.test.test19
 * @description: 列表字段乘积求和
 * @author: yangkai.shen
 * @date: Created in 2019-03-13 10:58
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test19 {
    public static void main(String[] args) {
        List<User> userList = Lists.newArrayList();
        userList.add(User.builder().n(1.0).salary(33.0).build());

        userList.add(User.builder().n(1.0).salary(33.0).build());

        double sum = userList.stream().mapToDouble(user -> user.getN() * user.getSalary()).sum();
        System.out.println("sum = " + sum);

    }

    @Data
    @Builder
    static class User {
        private Double salary;
        private Double n;
    }
}
