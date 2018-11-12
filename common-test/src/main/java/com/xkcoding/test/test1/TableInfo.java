package com.xkcoding.test.test1;

import lombok.Data;

/**
 * <p>
 * 表信息
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 表信息
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 16:19
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class TableInfo {
    private String id;
    private String dbName;
    private String schemaName;
    private String tableName;

    private DbInfo dbInfo;
}
