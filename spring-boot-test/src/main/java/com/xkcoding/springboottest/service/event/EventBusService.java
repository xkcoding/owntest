package com.xkcoding.springboottest.service.event;

import com.google.common.eventbus.EventBus;
import com.xkcoding.springboottest.config.event.EventListener;
import com.xkcoding.springboottest.entity.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * EventBus测试
 * </p>
 *
 * @package: com.xkcoding.springboottest.service.event
 * @description: EventBus测试
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 10:18
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class EventBusService implements InitializingBean {
    private final EventBus eventBus;

    private final EventListener eventListener;

    @Autowired
    public EventBusService(EventBus eventBus, EventListener eventListener) {
        this.eventBus = eventBus;
        this.eventListener = eventListener;
    }

    public void postEvent() {
        eventBus.post(new TestEvent(1L));
        log.info("event emit....");
    }

    @Override
    public void afterPropertiesSet() {
        eventBus.register(eventListener);
    }
}
