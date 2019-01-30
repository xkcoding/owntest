package com.xkcoding.springboottest.controller.enum_test;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 测试参数是枚举类型，自动转换
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.enum_test
 * @description: 测试参数是枚举类型，自动转换
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 13:56
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@RestController
@RequestMapping("/enum")
public class EnumTestController {

    @GetMapping("/get")
    public Dict testGet(QueryRequest request) {
        log.info("【get-request】= {}", JSONUtil.toJsonStr(request));
        return Dict.create().set("get-request", request);
    }

    @PostMapping("/post")
    public Dict testPost(@RequestBody QueryRequest request) {
        log.info("【post-request】= {}", JSONUtil.toJsonStr(request));
        return Dict.create().set("post-request", request);
    }

}
