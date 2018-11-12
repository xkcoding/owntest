package com.xkcoding.test.test1;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 异步任务工厂
 * </p>
 *
 * @package: com.mchz.datamanager.thread
 * @description: 异步任务工厂
 * @author: yangkai.shen
 * @date: Created in 2018/10/23 22:13
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ThreadTaskFactory {
    private static final ReentrantLock dbLock = new ReentrantLock();
    private static final Condition hasDb = dbLock.newCondition();
    private static final ReentrantLock tableLock = new ReentrantLock();
    private static final ReentrantLock viewLock = new ReentrantLock();

    public static TimerTask executeDbTask(DbInfo dbInfo) {
        return new TimerTask() {
            @Override
            public void run() {
                String id = dbInfo.getId();
                DbInfo exist = Neo.DB.get(id);
                if (ObjectUtil.isNull(exist)) {
                    // save
                    // 模拟耗时操作
                    simulateTime();
                    Neo.DB.put(id, dbInfo);
                } else {
                    // update
                    // 模拟耗时操作
                    simulateTime();
                    Neo.DB.replace(id, dbInfo);
                }
            }
        };
    }

    public static TimerTask executeTableTask(TableInfo tableInfo) {
        return new TimerTask() {
            @Override
            public void run() {
                String id = tableInfo.getId();
                TableInfo exist = Neo.TABLE.get(id);
                if (ObjectUtil.isNull(exist)) {
                    // save
                    // 处理父级
                    processDb(tableInfo);
                    Neo.TABLE.put(id, tableInfo);
                } else {
                    // update
                    // 模拟耗时操作
                    simulateTime();
                    Neo.TABLE.replace(id, tableInfo);
                }
            }
        };
    }

    private static void processDb(TableInfo tableInfo) {
        dbLock.lock();
        try {
            String dbName = tableInfo.getDbName();
            String id = IdUtil.getId(IdUtil.Type.DB, Dict.create().set("dbName", dbName));
            DbInfo exist = Neo.DB.get(id);
            if (ObjectUtil.isNull(exist)) {
                DbInfo dbInfo = new DbInfo();
                dbInfo.setId(id);
                dbInfo.setDbName(dbName);
                Neo.DB.put(id, dbInfo);
                tableInfo.setDbInfo(dbInfo);
            } else {
                tableInfo.setDbInfo(exist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbLock.unlock();
        }
    }

    /**
     * 模拟耗时操作
     */
    private static void simulateTime() {
        try {
            TimeUnit.SECONDS.sleep(RandomUtil.randomLong(1, 5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static TimerTask executeTask(Object obj) {
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println(DateUtil.now() + "\t【线程号：" + Thread.currentThread().getId() + "】\tobj = " + obj);
            }
        };
    }
}
