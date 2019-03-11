package com.xkcoding.demo;

import com.xkcoding.launcher.ScafflodApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @package: com.xkcoding.demo
 * @description: 启动器
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 09:45
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ScafflodApplication.run("demo", DemoApplication.class, args);
    }
}
