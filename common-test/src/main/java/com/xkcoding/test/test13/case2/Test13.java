package com.xkcoding.test.test13.case2;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * <p>
 * 测试 Disruptor 单生产者/单消费者模型
 * </p>
 *
 * @package: com.xkcoding.test.test13
 * @description: 测试 Disruptor
 * @author: yangkai.shen
 * @date: Created in 2019-01-24 16:00
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test13 {
    private static final Snowflake SNOWFLAKE = IdUtil.createSnowflake(1, 1);

    public static void main(String[] args) {
        OrderEventFactory eventFactory = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;

        ThreadFactory threadFactory = r -> {
            Thread t = new Thread(r);
            t.setName("disruptor-" + t.getId());
            return t;
        };

        Disruptor<OrderEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE, new YieldingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new OrderEventHandler());
        disruptor.start();

        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
       
        for (int i = 0; i < 10000; i++) {
            ringBuffer.publishEvent((event, sequence) -> {
                event.setId(SNOWFLAKE.nextId());
                event.setPrice(RandomUtil.randomBigDecimal());
            });
        }


        disruptor.shutdown();
    }
}
