package com.xkcoding.demo.controller;

import com.xkcoding.demo.model.User;
import com.xkcoding.scaffold.code.ScaffoldCode;
import com.xkcoding.scaffold.common.api.R;
import com.xkcoding.scaffold.log.annotations.ApiLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 测试Controller
 * </p>
 *
 * @package: com.xkcoding.demo.controller
 * @description: 测试Controller
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 16:08
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@Slf4j
@Api(tags = {"测试接口1"})
public class DemoController {
    @Autowired
    private ScaffoldCode scaffoldCode;

    @PostMapping({"", "/"})
    @ApiLog("测试日志")
    @ApiOperation(value = "测试", notes = "用户名")
    public R<User> test(@RequestBody User user) {
        log.info("【111111111】= {}", 111111111);
        log.error("【111111111】= {}", 111111111);
        return R.success(user);
    }

    @GetMapping(value = "/code/math.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> mathCode(HttpServletResponse response) {
        return scaffoldCode.generateMathCode(response);
    }

    @GetMapping("/verify/math/{code}")
    @ApiOperation(value = "验证数学验证码", notes = "验证数学验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "验证码", required = true)})
    public R<Boolean> verifyMathCode(HttpServletResponse response, @PathVariable String code) {
        boolean flag = scaffoldCode.validateMath(response, code);
        return R.status(flag, "验证码错误");
    }

    @GetMapping(value = "/code/random.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> randomCode(HttpServletResponse response) {
        return scaffoldCode.generateRandomCode(response);
    }

    @GetMapping("/verify/random/{code}")
    public R<Boolean> verifyCode(HttpServletResponse response, @PathVariable String code) {
        boolean flag = scaffoldCode.validateRandom(response, code);
        return R.status(flag, "验证码错误");
    }

    @GetMapping("/aop")
    public R<User> testAop(User user) {
        return R.success(user);
    }

}
