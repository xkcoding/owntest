package com.xkcoding.test.test12;

import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p>
 * 测试 hutool 的 SAX 读取 Excel
 * </p>
 *
 * @package: com.xkcoding.test.test12
 * @description: 测试 hutool 的 SAX 读取 Excel
 * @author: yangkai.shen
 * @date: Created in 2019-01-20 15:21
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test12 {
    public static void main(String[] args) {
        String filePath = "/Users/yangkai.shen/Desktop/采集作业-2_元数据导出.xlsx";
        Map<String, List<String>> headerGroup = Maps.newHashMap();

        AtomicInteger dbCount = new AtomicInteger(0);
        ExcelUtil.read07BySax(filePath, 0, ((sheetIndex, rowIndex, rowList) -> {
            System.out.println(rowList);
            dbCount.getAndIncrement();
            if (rowIndex == 0) {
                headerGroup.put("db", rowList.stream().map(String::valueOf).collect(Collectors.toList()));
            }
        }));

        AtomicInteger tableCount = new AtomicInteger(0);
        ExcelUtil.read07BySax(filePath, 1, ((sheetIndex, rowIndex, rowList) -> {
            tableCount.getAndIncrement();
            if (rowIndex == 0) {
                headerGroup.put("table", rowList.stream().map(String::valueOf).collect(Collectors.toList()));
            }
        }));

        AtomicInteger viewCount = new AtomicInteger(0);
        ExcelUtil.read07BySax(filePath, 2, ((sheetIndex, rowIndex, rowList) -> {
            viewCount.getAndIncrement();
            if (rowIndex == 0) {
                headerGroup.put("view", rowList.stream().map(String::valueOf).collect(Collectors.toList()));
            }
        }));

        AtomicInteger columnCount = new AtomicInteger(0);
        ExcelUtil.read07BySax(filePath, 3, ((sheetIndex, rowIndex, rowList) -> {
            columnCount.getAndIncrement();
            if (rowIndex == 0) {
                headerGroup.put("column", rowList.stream().map(String::valueOf).collect(Collectors.toList()));
            }
        }));

        System.out.println("【dbCount】= " + dbCount);
        System.out.println("【tableCount】= " + tableCount);
        System.out.println("【viewCount】= " + viewCount);
        System.out.println("【columnCount】= " + columnCount);
        System.out.println(JSONUtil.toJsonStr(headerGroup));

    }
}
