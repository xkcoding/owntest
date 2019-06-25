package com.xkcoding.test.test29;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试 Excel 的 VLOOKUP 函数，模拟假数据
 * </p>
 *
 * @package: com.xkcoding.test.test29
 * @description: 测试 Excel 的 VLOOKUP 函数，模拟假数据
 * @author: yangkai.shen
 * @date: Created in 2019-06-25 09:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test29 {
    /**
     * IF(VLOOKUP(A2,测试!$A$2:$A$674,1,FALSE)=A2,"存在")
     */
    public static void main(String[] args) {
        List<Map<String, String>> sheet1 = new ArrayList<>();
        List<Map<String, String>> sheet2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String uuid = IdUtil.fastUUID();
            Map<String, String> data1 = new HashMap<>();
            data1.put("银行", uuid);
            sheet1.add(data1);

            if (RandomUtil.randomInt(1, 10) < 7) {
                Map<String, String> data2 = new HashMap<>();
                data2.put("测试", uuid);
                sheet2.add(data2);
            }
        }

        String fileName = "/Users/yangkai.shen/Desktop/miniuniu.xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(fileName, "银行");
        writer.write(sheet1, true);
        writer.setSheet("小A");
        writer.write(sheet2);
        writer.close();
    }
}
