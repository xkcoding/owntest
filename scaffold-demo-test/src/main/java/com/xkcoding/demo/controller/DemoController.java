package com.xkcoding.demo.controller;

import cn.hutool.core.lang.Dict;
import com.xkcoding.common.api.R;
import com.xkcoding.log.annotations.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试Controller
 * </p>
 *
 * @package: com.xkcoding.demo.controller
 * @description: 测试Controller
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 09:49
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/01")
    public Dict demo01() {
        return Dict.create().set("word", "hello");
    }

    @GetMapping("/02")
    public R demo02(){
        return R.success();
    }

    @GetMapping("/03")
    @ApiLog("测试03")
    public R demo03(){
        return R.success();
    }
}
