package com.xkcoding.demo;

import com.xkcoding.scaffold.test.ScaffoldBaseTest;
import com.xkcoding.scaffold.test.ScaffoldTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 测试
 * </p>
 *
 * @package: com.xkcoding.demo
 * @description: 测试
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 10:48
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootTest
@ScaffoldTest(appName = "demo-test", profile = "test")
@Slf4j
public class DemoTest extends ScaffoldBaseTest {

    @Test
    public void test01() {
        log.info("1111111111");
        log.error("1111111111");
    }
}
