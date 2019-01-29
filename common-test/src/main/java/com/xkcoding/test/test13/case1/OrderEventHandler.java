package com.xkcoding.test.test13.case1;

import cn.hutool.json.JSONUtil;
import com.lmax.disruptor.EventHandler;

/**
 * <p>
 * 订单事件处理
 * </p>
 *
 * @package: com.xkcoding.test.test13
 * @description: 订单事件处理
 * @author: yangkai.shen
 * @date: Created in 2019-01-29 10:50
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) {
        System.err.println("【event】= " + JSONUtil.toJsonStr(event));
    }

}
