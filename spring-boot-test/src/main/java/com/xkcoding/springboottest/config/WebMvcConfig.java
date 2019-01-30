package com.xkcoding.springboottest.config;

import com.xkcoding.springboottest.config.convert.factory.IntegerCodeToEnumConverterFactory;
import com.xkcoding.springboottest.config.convert.factory.StringCodeToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * MVC通用配置
 * </p>
 *
 * @package: com.xkcoding.springboottest.config
 * @description: MVC通用配置
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 11:33
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 枚举类的转换器工厂 addConverterFactory
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerCodeToEnumConverterFactory());
        registry.addConverterFactory(new StringCodeToEnumConverterFactory());
    }
}