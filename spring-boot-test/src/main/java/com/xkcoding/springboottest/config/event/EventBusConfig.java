package com.xkcoding.springboottest.config.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * EventBus 配置
 * </p>
 *
 * @package: com.xkcoding.springboottest.config.event
 * @description: EventBus 配置
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 09:15
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
public class EventBusConfig {

    @Bean
    public EventBus executorService() {
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(20);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, workQueue, new BasicThreadFactory.Builder()
                .namingPattern("EventBus")
                .build());
        return new AsyncEventBus(executor);
    }
}
