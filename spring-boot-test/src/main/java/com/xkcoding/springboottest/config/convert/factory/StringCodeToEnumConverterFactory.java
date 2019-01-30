package com.xkcoding.springboottest.config.convert.factory;

import com.google.common.collect.Maps;
import com.xkcoding.springboottest.config.convert.StringToEnumConverter;
import com.xkcoding.springboottest.constants.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * <p>
 * 编码 -> 枚举 转化器工厂类
 * </p>
 *
 * @package: com.xkcoding.springboottest.config.convert.factory
 * @description: 编码 -> 枚举 转化器工厂类
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 11:09
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class StringCodeToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();

    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new StringToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }
}
