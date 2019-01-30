package com.xkcoding.springboottest.config.convert;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Maps;
import com.xkcoding.springboottest.constants.enums.GenderEnum;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * <p>
 * 编码 -> 性别 转换器
 * </p>
 *
 * @package: com.xkcoding.springboottest.config.convert
 * @description: 编码 -> 性别 转换器
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 11:14
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class StringCodeToGenderEnumConverter implements Converter<String, GenderEnum> {
    private Map<String, GenderEnum> enumMap = Maps.newHashMap();

    public StringCodeToGenderEnumConverter() {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            enumMap.put(genderEnum.getCode().toString(), genderEnum);
        }
    }

    @Override
    public GenderEnum convert(String source) {
        GenderEnum genderEnum = enumMap.get(source);
        if (ObjectUtil.isNull(genderEnum)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return genderEnum;
    }
}