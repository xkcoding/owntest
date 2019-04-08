package com.xkcoding.demo.config;

import com.xkcoding.demo.service.CustomLogServiceImpl;
import com.xkcoding.scaffold.log.service.LogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 日志配置
 * </p>
 *
 * @package: com.xkcoding.demo.config
 * @description: 日志配置
 * @author: yangkai.shen
 * @date: Created in 2019-03-13 14:13
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
public class LogConfig {
    @Bean
    public LogService logService() {
        return new CustomLogServiceImpl();
    }
}
