package com.xkcoding.test.test3;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateTime;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 测试hutool的BeanUtil的copyProperties
 * </p>
 *
 * @package: com.xkcoding.test.test3
 * @description: 测试hutool的BeanUtil的copyProperties
 * @author: yangkai.shen
 * @date: Created in 2018/11/21 11:28
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test3 {
    public static void main(String[] args) {
        Date1 date1 = new Date1();
        date1.setCreateTime(new DateTime());
        System.out.println("date1 = " + date1);
        Long1 long1 = new Long1();
        BeanUtil.copyProperties(date1, long1);
        System.out.println("long1 = " + long1);
        Date2 date2 = new Date2();
        Map<String, String> fieldMapping = Maps.newHashMap();
        fieldMapping.put("createTime", "startTime");
        BeanUtil.copyProperties(date1, date2, CopyOptions.create().setFieldMapping(fieldMapping));
        System.out.println("date2 = " + date2);
    }

    @Data
    static class Date1 {
        private Date createTime;
    }

    @Data
    static class Date2 {
        private Date startTime;
    }

    @Data
    static class Long1 {
        private Long createTime;
    }
}
