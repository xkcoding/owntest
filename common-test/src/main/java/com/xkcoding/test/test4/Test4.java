package com.xkcoding.test.test4;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.Date;

/**
 * <p>
 * 测试 ModelMapper 的 map 方法
 * </p>
 *
 * @package: com.xkcoding.test.test4
 * @description: 测试 ModelMapper 的 map 方法
 * @author: yangkai.shen
 * @date: Created in 2018/11/21 13:19
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test4 {
    public static void main(String[] args) {
        Date1 date1 = new Date1();
        date1.setCreateTime(new DateTime());
        System.out.println("date1 = " + date1);
        Long1 long1 = new Long1();
        ModelMapper date1Mapper = new ModelMapper();
        date1Mapper.addConverter((Converter<Date, Long>) context -> context.getSource().getTime());
        date1Mapper.map(date1, long1);
        System.out.println("long1 = " + long1);

        Date2 date2 = new Date2();
        ModelMapper date2Mapper = new ModelMapper();
        date2Mapper.addMappings(new PropertyMap<Date1, Date2>() {
            @Override
            protected void configure() {
                map().setStartTime(source.getCreateTime());
            }
        });
        date2Mapper.map(date1, date2);
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
