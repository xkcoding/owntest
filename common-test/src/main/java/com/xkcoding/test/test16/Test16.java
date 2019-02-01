package com.xkcoding.test.test16;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * <p>
 * MultiMap 获取同编号的数据下标
 * </p>
 *
 * @package: com.xkcoding.test.test16
 * @description: MultiMap 获取同编号的数据下标
 * @author: yangkai.shen
 * @date: Created in 2019-02-01 13:41
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test16 {
    public static void main(String[] args) {
        List<User> users = initData();
        Multimap<Integer, Integer> groupById = ArrayListMultimap.create();

        for (int i = 0; i < users.size(); i++) {
            groupById.put(users.get(i).getId(), i);
        }

        log.info("【groupById】= {}", JSONUtil.toJsonStr(groupById.asMap()));
    }

    private static List<User> initData() {
        List<User> users = Lists.newArrayList();

        users.add(User.builder().id(1).name("用户1").build());
        users.add(User.builder().id(2).name("用户2").build());
        users.add(User.builder().id(1).name("用户1").build());
        users.add(User.builder().id(3).name("用户3").build());
        users.add(User.builder().id(2).name("用户2").build());
        users.add(User.builder().id(4).name("用户4").build());

        return users;
    }


    @Data
    @Builder
    static class User {
        private Integer id;
        private String name;
    }
}
