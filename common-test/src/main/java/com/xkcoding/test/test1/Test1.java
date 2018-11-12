package com.xkcoding.test.test1;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;

import javax.swing.text.View;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 测试多线程处理，lock
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 测试多线程处理，lock
 * @author: yangkai.shen
 * @date: Created in 2018/11/12 16:12
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test1 {
    private static List<DbInfo> dbInfos = Lists.newArrayList();
    private static List<TableInfo> tableInfos = Lists.newArrayList();
    private static List<ViewInfo> viewInfos = Lists.newArrayList();
    private static List<ColumnInfo> columnInfos = Lists.newArrayList();

    private static List<Runnable> tasks = Lists.newArrayList();

    public static void main(String[] args) throws InterruptedException {
        initData();
        initTask();
        startTask();
    }

    /**
     * 启动任务
     */
    private static void startTask() {
        tasks.stream().parallel().forEach(Runnable::run);
    }

    /**
     * 初始化任务
     */
    private static void initTask() {
        initDbTask();
        initTableTask();
        initViewTask();
        initColumnTask();
    }

    private static void initColumnTask() {
        columnInfos.forEach((columnInfo -> {
            Runnable columnTask = () -> {
                String id = IdUtil.getId(IdUtil.Type.COLUMN, columnInfo);
                columnInfo.setId(id);
                ThreadTaskManager.me().executeTask(ThreadTaskFactory.executeTask(columnInfo));
            };
            tasks.add(columnTask);
        }));
    }

    private static void initViewTask() {
        viewInfos.forEach((viewInfo -> {
            Runnable viewTask = () -> {
                String id = IdUtil.getId(IdUtil.Type.VIEW, viewInfo);
                viewInfo.setId(id);
                ThreadTaskManager.me().executeTask(ThreadTaskFactory.executeTask(viewInfo));
            };
            tasks.add(viewTask);
        }));
    }

    private static void initTableTask() {
        tableInfos.forEach((tableInfo -> {
            Runnable tableTask = () -> {
                String id = IdUtil.getId(IdUtil.Type.TABLE, tableInfo);
                tableInfo.setId(id);
                ThreadTaskManager.me().executeTask(ThreadTaskFactory.executeTask(tableInfo));
            };
            tasks.add(tableTask);
        }));
    }

    private static void initDbTask() {
        dbInfos.forEach((dbInfo -> {
            Runnable dbTask = () -> {
                String id = IdUtil.getId(IdUtil.Type.DB, dbInfo);
                dbInfo.setId(id);
                ThreadTaskManager.me().executeTask(ThreadTaskFactory.executeDbTask(dbInfo));
            };
            tasks.add(dbTask);
        }));
    }

    /**
     * 初始化数据
     */
    private static void initData() {
        initDbInfo();
        initTableInfo();
        initViewInfo();
        initColumnInfo();
    }

    /**
     * 初始化列数据
     */
    private static void initColumnInfo() {
        for (int i = 0; i < 50; i++) {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setDbName("db-" + ((i / 50) + 1));
            columnInfo.setSchemaName("schema-" + ((i / 50) + 1));
            columnInfo.setTableName("table-" + ((i / 10) + 1));
            columnInfo.setTableType(1);
            columnInfo.setColumnName("column-" + ((i % 10) + 1));
            columnInfos.add(columnInfo);
        }

        for (int i = 0; i < 15; i++) {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setDbName("db-" + ((i / 15) + 1));
            columnInfo.setSchemaName("schema-" + ((i / 15) + 1));
            columnInfo.setTableName("view-" + ((i / 5) + 1));
            columnInfo.setTableType(0);
            columnInfo.setColumnName("column-" + ((i % 5) + 1));
            columnInfos.add(columnInfo);
        }

    }

    /**
     * 初始化视图数据
     */
    private static void initViewInfo() {
        for (int i = 0; i < 3; i++) {
            ViewInfo viewInfo = new ViewInfo();
            viewInfo.setDbName("db-" + ((i / 3) + 1));
            viewInfo.setSchemaName("schema-" + ((i / 3) + 1));
            viewInfo.setViewName("view-" + ((i % 3) + 1));
            viewInfos.add(viewInfo);
        }
    }

    /**
     * 初始化表数据
     */
    private static void initTableInfo() {
        for (int i = 0; i < 5; i++) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setDbName("db-" + ((i / 5) + 1));
            tableInfo.setSchemaName("schema-" + ((i / 5) + 1));
            tableInfo.setTableName("table-" + ((i % 5) + 1));
            tableInfos.add(tableInfo);
        }
    }

    /**
     * 初始化数据库数据
     */
    private static void initDbInfo() {
        DbInfo dbInfo = new DbInfo();
        dbInfo.setDbName("db-1");
        dbInfos.add(dbInfo);
    }

}
