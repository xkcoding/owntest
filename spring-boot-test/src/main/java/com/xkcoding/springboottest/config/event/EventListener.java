package com.xkcoding.springboottest.config.event;

import com.google.common.eventbus.Subscribe;
import com.xkcoding.springboottest.entity.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 事件监听
 * </p>
 *
 * @package: com.xkcoding.springboottest.config.event
 * @description: 事件监听
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 10:07
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
@Slf4j
public class EventListener {
    @Subscribe
    public void subscribeEvent1(TestEvent event) {
        log.info("test event dispatch,event is {}", event);
    }
}
