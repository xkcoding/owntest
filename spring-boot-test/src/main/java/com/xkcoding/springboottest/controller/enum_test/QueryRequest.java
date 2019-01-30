package com.xkcoding.springboottest.controller.enum_test;

import com.xkcoding.springboottest.constants.enums.GenderEnum;
import lombok.Data;

/**
 * <p>
 * 请求参数
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.enum_test
 * @description: 请求参数
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 14:02
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class QueryRequest {
    private GenderEnum gender;
}
