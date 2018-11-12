package com.xkcoding.test.test1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * 模拟neo4j数据库
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 模拟neo4j数据库
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 17:58
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Neo {
    /**
     * 模拟neo4j保存数据库
     */
    public static final ConcurrentMap<String, DbInfo> DB = new ConcurrentHashMap<>();
    /**
     * 模拟neo4j保存表
     */
    public static final ConcurrentMap<String, TableInfo> TABLE = new ConcurrentHashMap<>();
    /**
     * 模拟neo4j保存视图
     */
    public static final ConcurrentMap<String, ViewInfo> VIEW = new ConcurrentHashMap<>();
    /**
     * 模拟neo4j保存列
     */
    public static final ConcurrentMap<String, ColumnInfo> COLUMN = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("check map data");
    }
}
