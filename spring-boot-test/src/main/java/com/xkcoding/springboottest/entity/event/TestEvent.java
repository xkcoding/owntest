package com.xkcoding.springboottest.entity.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * EventBus测试
 * </p>
 *
 * @package: com.xkcoding.springboottest.entity.event
 * @description: EventBus测试
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 10:20
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestEvent {
    private Long jobId;
}
