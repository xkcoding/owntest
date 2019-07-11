package com.xkcoding.test.test30;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试 hutool Excel 效率
 * </p>
 *
 * @package: com.xkcoding.test.test30
 * @description: 测试 hutool Excel 效率
 * @author: yangkai.shen
 * @date: Created in 2019-06-27 13:41
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test30 {
    public static void main(String[] args) {
        // generateExcel(1000, 10);
        // generateExcel(2000, 10);
        // generateExcel(1000, 20);
        // generateExcel(1000, 30);
        // generateExcel(2000, 20);
        // generateExcel(10000, 1000);
        // readerExcel(1000, 10);
        // readerExcel(2000, 10);
        // readerExcel(1000, 20);
        // readerExcel(1000, 30);
        // readerExcel(2000, 20);
        readerExcel(10000, 1000);
    }

    private static void readerExcel(Integer totalRow, Integer totalColumn) {
        String fileName = "/Users/yangkai.shen/Desktop/性能测试-" + totalRow + "行" + totalColumn + "列.xlsx";
        System.out.println("开始读取文件【性能测试-" + totalRow + "行" + totalColumn + "列.xlsx】");
        TimeInterval timer = DateUtil.timer();

        ExcelReader reader = ExcelUtil.getReader(fileName);
        List<Map<String, Object>> data = reader.readAll();
        System.out.println("行数 = " + data.size() + "，列数 = " + data.get(0).size());
        long intervalMs = timer.intervalMs();
        System.out.println("【性能测试-" + totalRow + "行" + totalColumn + "列.xlsx】读取完成，耗时：" + intervalMs + " 毫秒");
    }

    public static void generateExcel(Integer totalRow, Integer totalColumn) {
        String fileName = "/Users/yangkai.shen/Desktop/性能测试-" + totalRow + "行" + totalColumn + "列.xlsx";
        FileUtil.del(fileName);
        System.out.println("开始生成文件【性能测试-" + totalRow + "行" + totalColumn + "列.xlsx】");
        TimeInterval timer = DateUtil.timer();
        timer.start();

        List<Map<String, String>> data = Lists.newArrayList();

        for (int i = 0; i < totalRow; i++) {
            Map<String, String> rowData = Maps.newHashMap();
            for (int j = 0; j < totalColumn; j++) {
                rowData.put("测试表头" + (j + 1), "第" + (i + 1) + "行" + "," + "第" + (j + 1) + "列");
            }
            data.add(rowData);
        }

        ExcelWriter writer = ExcelUtil.getBigWriter(fileName);
        writer.write(data, true);
        writer.close();
        data.clear();
        long intervalMs = timer.intervalMs();
        System.out.println("【性能测试-" + totalRow + "行" + totalColumn + "列.xlsx】生成完成，耗时：" + intervalMs + " 毫秒");
    }
}
