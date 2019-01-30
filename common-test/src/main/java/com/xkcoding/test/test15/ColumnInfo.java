package com.xkcoding.test.test15;

import lombok.Data;

/**
 * <p>
 * 字段实体
 * </p>
 *
 * @package: com.xkcoding.test.test15
 * @description: 字段实体
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 10:25
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class ColumnInfo {
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;
}
