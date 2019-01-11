package com.xkcoding.test.test11;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 测试Hutool的JsonArray对象转List
 * </p>
 *
 * @package: com.xkcoding.test.test11
 * @description: 测试Hutool的JsonArray对象转List
 * @author: yangkai.shen
 * @date: Created in 2019-01-10 17:37
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test11 {
    public static void main(String[] args) {
        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        List<User> userList = JSONUtil.toList(JSONUtil.parseArray(jsonArr), User.class);

        System.out.println(userList);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class User {
        private Integer id;
        private String name;
    }
}
