package com.xkcoding.test.test1;

import lombok.Data;

/**
 * <p>
 * 视图信息
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 视图信息
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 16:20
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class ViewInfo {
    private String id;
    private String dbName;
    private String schemaName;
    private String viewName;

    private DbInfo dbInfo;
}
