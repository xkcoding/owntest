package com.xkcoding.test.test2;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.List;

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
    public static void main(String[] args) {
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
        File zip = ZipUtil.zip("/Users/yangkai.shen/Desktop/test.xlsx", "/Users/yangkai.shen/Desktop/test.zip",true);
        System.out.println(zip.getAbsolutePath());
    }
}
