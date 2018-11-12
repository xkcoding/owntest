package com.xkcoding.test.test1;

import lombok.Data;

/**
 * <p>
 * 列信息
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 列信息
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 16:21
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class ColumnInfo {
    private String id;
    private String dbName;
    private String schemaName;
    private String tableName;
    private Integer tableType;
    private String columnName;

    private TableInfo tableInfo;
    private ViewInfo viewInfo;
}
