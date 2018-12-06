package com.xkcoding.test.test2;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试
 * </p>
 *
 * @package: com.xkcoding.test.test2
 * @description: 测试
 * @author: yangkai.shen
 * @date: Created in 2018-12-05 13:33
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private Integer age;
    private String desc;
}
