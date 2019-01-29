package com.xkcoding.test.test13.case1;

import com.lmax.disruptor.EventFactory;

/**
 * <p>
 * 订单事件工厂类
 * </p>
 *
 * @package: com.xkcoding.test.test13
 * @description: 订单事件工厂类
 * @author: yangkai.shen
 * @date: Created in 2019-01-29 10:41
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
