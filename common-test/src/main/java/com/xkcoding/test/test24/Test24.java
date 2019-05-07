package com.xkcoding.test.test24;

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
 * 测试 disruptor 多边形操作，多生产者 单消费者
 * <p>
 * 流程如下：
 * <pre>
 *              +---+  串行   +---+
 *         +--->+ A +------->+ C +---+
 *         |    +---+        +---+   |
 *         |                         |
 * +---+   |                         |   +---+   +---+
 * |开始+-->+                         +-->+ E +-->+ F |
 * +---+   |                         |   +---+   +---+
 *         |                         |
 *         |    +---+  串行   +---+   |
 *         +--->+ B +------->+ D +---+
 *              +---+        +---+
 * </pre>
 *
 * </p>
 *
 * @package: com.xkcoding.test.test24
 * @description: 测试 disruptor 多边形操作，多生产者 单消费者
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 14:08
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test24 {
    public static void main(String[] args) throws InterruptedException {
        // 事件工厂类对象
        ExportEventFactory factory = new ExportEventFactory();

        // 声明RingBuffer大小，必须为 2 的 n 次方
        int ringBufferSize = 1024 * 1024;

        // 创建 disruptor，需要事件工厂、RingBuffer大小、线程池
        Disruptor<ExportEvent> disruptor = new Disruptor<>(factory, ringBufferSize, ThreadUtil.newNamedThreadFactory("disruptor-", false), ProducerType.MULTI, new YieldingWaitStrategy());

        // 模拟10个线程
        int threadNum = 1024;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        // 设置消费者
        boolean needSleep = false;
        EventHandler<ExportEvent> processData1 = new SleepExportEventHandler(needSleep) {
            @Override
            protected void processSleep(ExportEvent event) {
                simulateSleep(event.getName(), "处理第一步数据", RandomUtil.randomInt(1, 3));
            }

            @Override
            void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
                event.setData1("data1");
                log.error("【{}处理第一步数据】{}", event.getName(), event.getData1());
            }
        };

        EventHandler<ExportEvent> processData2 = new SleepExportEventHandler(needSleep) {
            @Override
            protected void processSleep(ExportEvent event) {
                simulateSleep(event.getName(), "处理第二步数据", RandomUtil.randomInt(1, 3));
            }

            @Override
            void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
                event.setData2("data2");
                log.error("【{}处理第二步数据】{}", event.getName(), event.getData2());
            }
        };

        EventHandler<ExportEvent> processData3 = new SleepExportEventHandler(needSleep) {
            @Override
            protected void processSleep(ExportEvent event) {
                simulateSleep(event.getName(), "处理第三步数据", RandomUtil.randomInt(3, 6));
            }

            @Override
            void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
                event.setData3(event.getData1() + " - data3");
                log.error("【{}处理第三步数据】{}", event.getName(), event.getData3());
            }
        };

        EventHandler<ExportEvent> processData4 = new SleepExportEventHandler(needSleep) {
            @Override
            protected void processSleep(ExportEvent event) {
                simulateSleep(event.getName(), "处理第四步数据", RandomUtil.randomInt(3, 6));
            }

            @Override
            void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
                event.setData4(event.getData2() + " - data4");
                log.error("【{}处理第四步数据】{}", event.getName(), event.getData4());
            }
        };

        EventHandler<ExportEvent> processData5 = new SleepExportEventHandler(needSleep) {
            @Override
            protected void processSleep(ExportEvent event) {
                simulateSleep(event.getName(), "整合数据", RandomUtil.randomInt(1, 3));
            }

            @Override
            void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
                event.setData(event.getData3() + "::" + event.getData4());
                log.error("【{}整合数据】{}", event.getName(), event.getData());
            }
        };

        // 并行处理 1,2
        disruptor.handleEventsWith(processData1, processData2);
        // 串行处理 1,3
        disruptor.after(processData1).handleEventsWith(processData3);
        // 串行处理 2,4
        disruptor.after(processData2).handleEventsWith(processData4);
        // 3,4 结束处理 5
        disruptor.after(processData3, processData4).handleEventsWith(processData5);
        disruptor.after(processData5).handleEventsWith(new ExportEventHandler(countDownLatch, needSleep));
        // 设置异常处理
        disruptor.setDefaultExceptionHandler(new FatalExceptionHandler());

        // 启动 disruptor
        disruptor.start();

        // 获取当前 disruptor 的 RingBuffer
        RingBuffer<ExportEvent> ringBuffer = disruptor.getRingBuffer();
        // 设置生产者
        ExportEventPublisher publisher = new ExportEventPublisher(ringBuffer);

        String filePath = "/Users/yangkai.shen/Desktop/disruptor-advance.txt";
        FileUtil.del(filePath);
        File file = FileUtil.touch(filePath);

        List<ExportThread> threadList = Lists.newArrayList();
        for (int i = 0; i < threadNum; i++) {
            threadList.add(new ExportThread(publisher, file, "任务" + i));
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

    /**
     * 模拟延时
     *
     * @param name 名称
     * @param step 步骤
     */
    public static void simulateSleep(String name, String step, int second) {
        // 延迟
        ThreadUtil.sleep(second, TimeUnit.SECONDS);
        log.debug("【{}{}】耗时：{} 秒", name, step, second);
    }
}

@Slf4j
class ExportThread implements Runnable {
    private ExportEventPublisher publisher;
    private File file;
    private String name;

    public ExportThread(ExportEventPublisher publisher, File file, String name) {
        this.publisher = publisher;
        this.file = file;
        this.name = name;
    }

    @Override
    public void run() {
        log.debug("【准备数据】{}已发布", name);
        publisher.sendData(new ExportEvent(name, null, file, null, null, null, null));
    }
}

/**
 * <p>
 * 导出事件
 * </p>
 *
 * @package: com.xkcoding.test.test24
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
    private String name;
    private String data;
    private File file;
    private String data1;
    private String data2;
    private String data3;
    private String data4;
}

/**
 * <p>
 * 导出事件工厂类
 * </p>
 *
 * @package: com.xkcoding.test.test24
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
 * @package: com.xkcoding.test.test24
 * @description: 导出事件处理类
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 10:25
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
class ExportEventHandler extends SleepExportEventHandler {
    private final CountDownLatch countDownLatch;

    ExportEventHandler(CountDownLatch countDownLatch, boolean needSleep) {
        super(needSleep);
        this.countDownLatch = countDownLatch;
    }


    /**
     * 延时处理
     *
     * @param event 事件
     */
    @Override
    protected void processSleep(ExportEvent event) {
        Test24.simulateSleep(event.getName(), "导出最终数据", RandomUtil.randomInt(3, 6));
    }

    /**
     * 当生产者往 {@link RingBuffer} 发布事件时会被调用.
     *
     * @param event      在 {@link RingBuffer} 中的事件
     * @param sequence   被处理的事件的位置
     * @param endOfBatch 声明在 {@link RingBuffer} 中是否为批量事件的最后一个标记
     */
    @Override
    void processEvent(ExportEvent event, long sequence, boolean endOfBatch) {
        // 写文件，打印模拟
        FileUtil.appendUtf8Lines(Collections.singletonList(event.getName() + event.getData()), event.getFile());
        countDownLatch.countDown();
    }
}

/**
 * <p>
 * 休眠事件处理
 * </p>
 *
 * @package: com.xkcoding.test.test24
 * @description: 休眠事件处理
 * @author: yangkai.shen
 * @date: Created in 2019-05-05 22:06
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
abstract class SleepExportEventHandler implements EventHandler<ExportEvent> {
    private final boolean needSleep;

    protected SleepExportEventHandler(boolean needSleep) {
        this.needSleep = needSleep;
    }

    @Override
    public void onEvent(ExportEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (needSleep) {
            processSleep(event);
        }

        processEvent(event, sequence, endOfBatch);
    }

    /**
     * 延时处理
     */
    protected void processSleep(ExportEvent event) {
    }

    /**
     * 当生产者往 {@link RingBuffer} 发布事件时会被调用.
     *
     * @param event      在 {@link RingBuffer} 中的事件
     * @param sequence   被处理的事件的位置
     * @param endOfBatch 声明在 {@link RingBuffer} 中是否为批量事件的最后一个标记
     */
    abstract void processEvent(ExportEvent event, long sequence, boolean endOfBatch);
}

/**
 * <p>
 * 导出事件生产者
 * </p>
 *
 * @package: com.xkcoding.test.test24
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
