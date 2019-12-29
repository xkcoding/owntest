package com.xkcoding.test.test43;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * <p>
 * 测试 parallelStream 是否会阻塞主线程
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/12/29 16:31
 */
@Slf4j
public class Test43 {
    public static void main(String[] args) {
        normalTest();
        parallelTest();
    }

    private static void normalTest() {
        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);

        final TimeInterval timer = DateUtil.timer();
        timer.start();
        integers.forEach(i -> {
            int time = i * 100 + RandomUtil.randomInt(1000, 5000);
            ThreadUtil.sleep(time);
            log.info(i + " 睡眠完毕！" + time);
        });

        System.out.println("over!" + timer.intervalMs());
    }

    public static void parallelTest() {
        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);

        final TimeInterval timer = DateUtil.timer();
        timer.start();
        integers.parallelStream().forEach(i -> {
            int time = i * 100 + RandomUtil.randomInt(1000, 5000);
            ThreadUtil.sleep(time);
            log.info(i + " 睡眠完毕！" + time);
        });

        System.out.println("over!" + timer.intervalMs());
    }
}
