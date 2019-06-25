package com.xkcoding.test.test28;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.Collections;

/**
 * <p>
 * 测试hutool JsonArray JsonObj
 * </p>
 *
 * @package: com.xkcoding.test.test28
 * @description: 测试hutool JsonArray JsonObj
 * @author: yangkai.shen
 * @date: Created in 2019-06-19 16:08
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test28 {
    public static void main(String[] args) {
        String data = "[{\"YEAR_CNAME\":\"test2\",\"ACTIVE_FLAG\":\"2\",\"INSERT_TIME\":\"2019-05-27T15:16:37\",\"UPDATE_TIME\":\"2019-05-27T15:16:37\",\"YEAR_CODE\":64},{\"YEAR_CNAME\":\"test2\",\"ACTIVE_FLAG\":\"2\",\"INSERT_TIME\":\"2019-05-27T15:16:37\",\"UPDATE_TIME\":\"2019-05-27T15:16:37\",\"YEAR_CODE\":64}]";

        if (JSONUtil.isJsonArray(data)) {

            JSONArray jsonArray = JSONUtil.parseArray(data);

            for (Object jsonObj : jsonArray) {
                JSONArray singleDataArr = JSONUtil.parseArray(Collections.singletonList(jsonObj));
                System.out.println("JSONUtil.toJsonStr(singleDataArr) = " + JSONUtil.toJsonStr(singleDataArr));
            }

        }
    }
}
