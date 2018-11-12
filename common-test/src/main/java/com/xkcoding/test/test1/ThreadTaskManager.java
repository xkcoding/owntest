package com.xkcoding.test.test1;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 异步任务线程管理
 * </p>
 *
 * @package: com.xkcoding.test.test1
 * @description: 异步任务线程管理
 * @author: yangkai.shen
 * @date: Created in 2018/10/23 22:10
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ThreadTaskManager {

	private final static ThreadTaskManager INSTANCE = new ThreadTaskManager();
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(20, new BasicThreadFactory.Builder().namingPattern("Async-Thread-%d").build());

	private ThreadTaskManager() {
	}

	public static ThreadTaskManager me() {
		return INSTANCE;
	}

	public void executeTask(TimerTask task) {
		executor.schedule(task, 2000, TimeUnit.MILLISECONDS);
	}
}
