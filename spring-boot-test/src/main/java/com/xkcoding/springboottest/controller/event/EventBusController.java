package com.xkcoding.springboottest.controller.event;

import com.xkcoding.springboottest.service.event.EventBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * EventBus测试
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.event
 * @description: EventBus测试
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 10:28
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/eventbus")
public class EventBusController {
    private final EventBusService eventBusService;

    @Autowired
    public EventBusController(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }

    @GetMapping("/send")
    public String sendMessage() {
        // 模拟触发消息
        eventBusService.postEvent();
        return "ok";
    }
}
