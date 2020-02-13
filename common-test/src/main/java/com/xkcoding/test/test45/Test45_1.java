package com.xkcoding.test.test45;

import cn.hutool.core.date.StopWatch;

/**
 * <p>
 * 测试 hutool 的 stopwatch
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/13 10:51
 */
public class Test45_1 {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("task 1");
        try {
            stopWatch.start("task 1.1");
            Thread.sleep(2000);
            stopWatch.stop();

            stopWatch.start("task 1.2");
            Thread.sleep(2000);
            stopWatch.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(stopWatch.prettyPrint());
    }
}
