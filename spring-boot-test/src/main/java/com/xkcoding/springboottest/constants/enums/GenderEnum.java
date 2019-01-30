package com.xkcoding.springboottest.constants.enums;

import com.xkcoding.springboottest.constants.BaseEnum;
import lombok.Getter;

/**
 * <p>
 * 性别枚举
 * </p>
 *
 * @package: com.xkcoding.springboottest.constants.enums
 * @description: 性别枚举
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 11:06
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
public enum GenderEnum implements BaseEnum {
    /**
     * 男
     */
    MALE(0),
    /**
     * 女
     */
    FEMALE(1);

    /**
     * 性别编码
     */
    private Integer code;

    GenderEnum(int code) {
        this.code = code;
    }

}
