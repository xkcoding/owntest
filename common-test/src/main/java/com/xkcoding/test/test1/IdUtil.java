package com.xkcoding.test.test1;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * <p>
 * Id 工具类
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: Id 工具类
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 16:23
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class IdUtil {
    enum Type {
        /**
         * DB
         */
        DB,
        /**
         * TABLE
         */
        TABLE,
        /**
         * VIEW
         */
        VIEW,
        /**
         * COLUMN
         */
        COLUMN
    }

    /**
     * 获取id
     *
     * @param type 类型
     * @param obj  对象
     * @return id
     */
    public static String getId(Type type, Object obj) {
        Map<String, Object> params = BeanUtil.beanToMap(obj);
        switch (type) {
            case DB:
                return StrUtil.format("db@{dbName}", params);
            case VIEW:
                return StrUtil.format("view@{dbName}@{schemaName}@{viewName}", params);
            case TABLE:
                return StrUtil.format("table@{dbName}@{schemaName}@{tableName}", params);
            case COLUMN:
                return StrUtil.format("column@{dbName}@{schemaName}@{tableName}@{columnName}", params);
            default:
                return null;
        }
    }

}
