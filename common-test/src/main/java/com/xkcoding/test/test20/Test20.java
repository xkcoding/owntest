package com.xkcoding.test.test20;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 测试 [[1,2,3],[4,5,6]] 字符串转json
 * </p>
 *
 * @package: com.xkcoding.test.test20
 * @description: 测试 [[1,2,3],[4,5,6]] 字符串转json
 * @author: yangkai.shen
 * @date: Created in 2019-03-19 13:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test20 {
    public static void main(String[] args) {
        String json = "[[1,2,3],[4,5,6]]";
        boolean isJsonArray = JSONUtil.isJsonArray(json);
        Arr arr = new Arr();
        if (isJsonArray) {
            JSONArray jsonArray = new JSONArray(json);
            List<List> list = JSONUtil.toList(jsonArray, List.class);
            arr.setData(list);
        }

        System.out.println("JSONUtil.toJsonPrettyStr(arr) = " + JSONUtil.toJsonPrettyStr(arr));

    }

    @Data
    static class Arr {
        List<List> data;
    }
}
