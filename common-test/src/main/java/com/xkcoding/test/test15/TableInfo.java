package com.xkcoding.test.test15;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 表实体
 * </p>
 *
 * @package: com.xkcoding.test.test15
 * @description: 表实体
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 09:43
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class TableInfo {
    private String tableName;
    private String tableComment;
    private Date createTime;
}
