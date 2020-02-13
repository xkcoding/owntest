package com.xkcoding.test.test45;

/**
 * <p>
 * 测试 stopwatch
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/13 10:51
 */
public class Test45 {
    public static void main(String[] args) {
        Stopwatchs.start("task 1");
        try {
            Stopwatchs.start("task 1.1");
            Thread.sleep(2000);
            Stopwatchs.end();

            Stopwatchs.start("task 1.2");
            Thread.sleep(2000);
            Stopwatchs.end();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Stopwatchs.end();
        }

        System.out.println(Stopwatchs.getTimingStat());
        final long elapsed = Stopwatchs.getElapsed("task 1");
        System.out.println(elapsed);

        Stopwatchs.release();
        System.out.println(Stopwatchs.getTimingStat());


    }
}
