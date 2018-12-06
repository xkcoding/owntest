package com.xkcoding.springboottest.entity.hutool.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 测试Hutool的ExcelUtil
 * </p>
 *
 * @package: com.xkcoding.springboottest.entity.hutool.excel
 * @description: 测试Hutool的ExcelUtil
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 16:14
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class ExcelModel {
    private String id;
    private String name;
    private Date birthday;
    private Integer age;
}
