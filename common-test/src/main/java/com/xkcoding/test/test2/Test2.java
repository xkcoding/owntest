package com.xkcoding.test.test2;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 测试Excel导出，并打成压缩包
 * </p>
 *
 * @package: com.xkcoding.test.test2
 * @description: 测试Excel导出，并打成压缩包
 * @author: yangkai.shen
 * @date: Created in 2018/11/13 16:35
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test2 {
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new BasicThreadFactory.Builder()
            .namingPattern("Export")
            .build());

    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();
        //                test2.test1();
        //        test2.test2();
        test2.test3();
    }

    public void test3() {
        String fileName = "/Users/yangkai.shen/Desktop/multi-sheet.xlsx";
        FileUtil.del(fileName);
        try (ExcelWriter writer = ExcelUtil.getBigWriter(fileName)) {
            for (int i = 1; i <= 4; i++) {
                writer.setSheet("sheet" + i);
                List<User> users = Lists.newArrayList(new User().setName(i + "-user-1")
                        .setAge(11 * i)
                        .setDesc(i + "-desc-1"), new User().setName(i + "-user-2").setAge(11).setDesc(i + "-desc-2"));
                writer.write(users);
                if (i == 2) {
                    users = Lists.newArrayList(new User().setName(i + "-user-1")
                            .setAge(11 * i)
                            .setDesc(i + "-desc-1"), new User().setName(i + "-user-2")
                            .setAge(11)
                            .setDesc(i + "-desc-2"));
                    writer.write(users);
                }
            }
        } catch (Exception e) {
            // TODO: 异常处理
            System.err.println(e);
        }
    }

    /**
     * 测试多线程导出不同sheet
     */
    public void test2() throws InterruptedException {
        String fileName = "/Users/yangkai.shen/Desktop/multi-sheet.xlsx";
        FileUtil.del(fileName);
        BigExcelWriter bigWriter = ExcelUtil.getBigWriter(fileName);
        bigWriter.close();
        CountDownLatch latch = new CountDownLatch(4);
        executor.execute(executeSheet(fileName, 1, latch));
        executor.execute(executeSheet(fileName, 2, latch));
        executor.execute(executeSheet(fileName, 3, latch));
        executor.execute(executeSheet(fileName, 4, latch));
        System.out.println("waiting......");
        latch.await();
        System.out.println("finished......");
        System.out.println(fileName);
    }

    private Runnable executeSheet(String fileName, Integer sheetNum, CountDownLatch latch) {
        return () -> {
            System.out.println("process sheet " + sheetNum);
            try (ExcelWriter writer = ExcelUtil.getBigWriter(fileName)) {
                writer.setSheet("sheet" + sheetNum);
                List<User> users = Lists.newArrayList(new User().setName(sheetNum + "-user-1")
                        .setAge(11 * sheetNum)
                        .setDesc(sheetNum + "-desc-1"), new User().setName(sheetNum + "-user-2")
                        .setAge(11)
                        .setDesc(sheetNum + "-desc-2"));
                writer.write(users);
            } catch (Exception e) {
                // TODO: 异常处理
                System.err.println(e);
            } finally {
                latch.countDown();
            }
        };
    }

    /**
     * 测试基础导出
     */
    public void test1() {
        List<?> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd", DateUtil.date(), 3.22676575765);
        List<?> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1", DateUtil.date(), 250.7676);
        List<?> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2", DateUtil.date(), 0.111);
        List<?> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3", DateUtil.date(), 35);
        List<?> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4", DateUtil.date(), 28.00);

        List<List<?>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        FileUtil.del("/Users/yangkai.shen/Desktop/test.xlsx");
        BigExcelWriter writer = ExcelUtil.getBigWriter("/Users/yangkai.shen/Desktop/test.xlsx");
        writer.merge(rows.size(), "测试导出");
        // 一次性写出内容，使用默认样式
        writer.write(rows);
        // 关闭writer，释放内存
        writer.close();
        File zip = ZipUtil.zip("/Users/yangkai.shen/Desktop/test.xlsx", "/Users/yangkai.shen/Desktop/test.zip", true);
        System.out.println(zip.getAbsolutePath());
    }
}
