package com.xkcoding.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller
 * @description: 测试
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 10:33
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping
public class TestController {
    @GetMapping
    public String test() {
        return "test";
    }
}
