package com.xkcoding.test.test18;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 编写一个多线程程序，打印当前线程名，每隔300毫秒输出一次，总共输出10次
 * </p>
 *
 * @package: com.xkcoding.test.test18
 * @description: 编写一个多线程程序，打印当前线程名，每隔300毫秒输出一次，总共输出10次
 * @author: yangkai.shen
 * @date: Created in 2019-03-07 14:21
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@AllArgsConstructor
public class Test18 implements Runnable {

    public static void main(String[] args) {
        Test18 test18 = new Test18(10, 300);
        test18.run();
    }

    private Integer count;
    private Integer sleep;

    @Override
    public void run() {
        int start = 0;
        do {
            System.out.println(Thread.currentThread().getName());
            start++;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                // ignore
            }
        } while (start < count);
    }
}
