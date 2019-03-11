package com.xkcoding.demo.config;

import com.xkcoding.demo.service.LogServiceImpl;
import com.xkcoding.demo.service.SecurityServiceImpl;
import com.xkcoding.log.service.LogService;
import com.xkcoding.log.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 日志配置类
 * </p>
 *
 * @package: com.xkcoding.demo.config
 * @description: 日志配置类
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 10:41
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
public class LogConfig {

    /**
     * 自定义日志生成操作
     */
    @Bean
    public LogService logService() {
        return new LogServiceImpl();
    }

    /**
     * 自定义日志操作人
     */
    @Bean
    public SecurityService securityService() {
        return new SecurityServiceImpl();
    }
}
