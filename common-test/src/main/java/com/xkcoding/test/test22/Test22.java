package com.xkcoding.test.test22;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 测试 disruptor 多生产者 单消费者模型
 * </p>
 *
 * @package: com.xkcoding.test.test22
 * @description: 测试 disruptor 多生产者 单消费者模型
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:19
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test22 {
    public static void main(String[] args) throws InterruptedException {
        // 事件工厂类对象
        ExportEventFactory factory = new ExportEventFactory();

        // 声明RingBuffer大小，必须为 2 的 n 次方
        int ringBufferSize = 1024;

        // 创建 disruptor，需要事件工厂、RingBuffer大小、线程池
        Disruptor<ExportEvent> disruptor = new Disruptor<>(factory, ringBufferSize, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new YieldingWaitStrategy());

        // 模拟10个线程
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        // 设置消费者
        disruptor.handleEventsWith(new ExportEventHandler(countDownLatch));
        // 设置异常处理
        disruptor.setDefaultExceptionHandler(new FatalExceptionHandler());

        // 启动 disruptor
        disruptor.start();

        // 获取当前 disruptor 的 RingBuffer
        RingBuffer<ExportEvent> ringBuffer = disruptor.getRingBuffer();
        // 设置生产者
        ExportEventPublisher publisher = new ExportEventPublisher(ringBuffer);

        String filePath = "/Users/yangkai.shen/Desktop/disruptor-multi.txt";
        FileUtil.del(filePath);
        File file = FileUtil.touch(filePath);

        List<ExportThread> threadList = Lists.newArrayList();
        for (int i = 0; i < threadNum; i++) {
            threadList.add(new ExportThread(publisher, file));
        }

        TimeInterval timer = DateUtil.timer();
        timer.start();
        threadList.forEach(ThreadUtil::execute);
        countDownLatch.await();
        long interval = timer.intervalMs();
        log.info("【总耗时】{} 毫秒", interval);

        // 停止disruptor
        disruptor.shutdown();
    }
}

@Slf4j
class ExportThread implements Runnable {
    private ExportEventPublisher publisher;
    private File file;

    public ExportThread(ExportEventPublisher publisher, File file) {
        this.publisher = publisher;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            // 延迟
            int second = RandomUtil.randomInt(3, 5);
            TimeUnit.SECONDS.sleep(second);
            log.debug("【获取数据】耗时：{} 秒", second);
            String data = RandomUtil.randomString(RandomUtil.randomInt(20, 30));
            publisher.sendData(new ExportEvent(data, file));
            log.debug("【发布数据】{}", data);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}

/**
 * <p>
 * 导出事件
 * </p>
 *
 * @package: com.xkcoding.test.test22
 * @description: 导出事件
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:23
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class ExportEvent {
    private String data;
    private File file;
}

/**
 * <p>
 * 导出事件工厂类
 * </p>
 *
 * @package: com.xkcoding.test.test22
 * @description: 导出事件工厂类
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:23
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
class ExportEventFactory implements EventFactory<ExportEvent> {

    @Override
    public ExportEvent newInstance() {
        return new ExportEvent();
    }
}

/**
 * <p>
 * 导出事件处理类
 * </p>
 *
 * @package: com.xkcoding.test.test22
 * @description: 导出事件处理类
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:25
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
class ExportEventHandler implements EventHandler<ExportEvent> {
    private final CountDownLatch countDownLatch;

    ExportEventHandler(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }


    /**
     * 当生产者往 {@link RingBuffer} 发布事件时会被调用.
     *
     * @param event      在 {@link RingBuffer} 中的事件
     * @param sequence   被处理的事件的位置
     * @param endOfBatch 声明在 {@link RingBuffer} 中是否为批量事件的最后一个标记
     * @throws Exception 异常
     */
    @Override
    public void onEvent(ExportEvent event, long sequence, boolean endOfBatch) throws Exception {
        // 写文件，打印模拟
        log.error("【处理数据】{}", event.getData());
        FileUtil.appendUtf8Lines(Collections.singletonList(event.getData()), event.getFile());
        countDownLatch.countDown();
    }
}

/**
 * <p>
 * 导出事件生产者
 * </p>
 *
 * @package: com.xkcoding.test.test22
 * @description: 导出事件生产者
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:32
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
class ExportEventPublisher {
    private final RingBuffer<ExportEvent> ringBuffer;

    public ExportEventPublisher(RingBuffer<ExportEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<ExportEvent, ExportEvent> TRANSLATOR = (event, sequence, data) -> BeanUtil
            .copyProperties(data, event);

    /**
     * 发布消息
     *
     * @param data 数据
     */
    public void sendData(ExportEvent data) {
        ringBuffer.publishEvent(TRANSLATOR, data);
    }

}
