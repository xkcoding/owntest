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
public class IntegerCodeToGenderEnumConverter implements Converter<Integer, GenderEnum> {
    private Map<Integer, GenderEnum> enumMap = Maps.newHashMap();

    public IntegerCodeToGenderEnumConverter() {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            enumMap.put(genderEnum.getCode(), genderEnum);
        }
    }

    @Override
    public GenderEnum convert(Integer source) {
        GenderEnum genderEnum = enumMap.get(source);
        if (ObjectUtil.isNull(genderEnum)) {
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
        return genderEnum;
    }
}