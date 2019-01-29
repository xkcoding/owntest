package com.xkcoding.test.test13.case1;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.RingBuffer;

/**
 * <p>
 * 订单事件生产者
 * </p>
 *
 * @package: com.xkcoding.test.test13
 * @description: 订单事件生产者
 * @author: yangkai.shen
 * @date: Created in 2019-01-29 15:21
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class OrderEventProducer {
    private final RingBuffer<OrderEvent> ringBuffer;
    private static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件
     * 它的参数会用过事件传递给消费者
     */
    public void onData() {
        //1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();
        try {
            //2.用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
            OrderEvent event = ringBuffer.get(sequence);
            //3.获取要通过事件传递的业务数据
            event.setId(SNOWFLAKE.nextId());
            event.setPrice(RandomUtil.randomBigDecimal());
        } finally {
            //4.发布事件
            //注意，最后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；
            // 如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
            ringBuffer.publish(sequence);
        }
    }
}
