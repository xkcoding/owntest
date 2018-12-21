package com.xkcoding.test.test10;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * <p>
 * 测试解析SQL，获取字段名和类型
 * </p>
 *
 * @package: com.xkcoding.test.test10
 * @description: 测试解析SQL，获取字段名和类型
 * @author: yangkai.shen
 * @date: Created in 2018-12-21 17:15
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test10 {
    public static void main(String[] args) {
        String sql = "CREATE TABLE \" \" (\" cH Ange \" NUMBER(*,0), \" \" NVARCHAR2(50), \"$身份证\" NVARCHAR2(50), \"k.t\" NVARCHAR2(50), \"t\" TIMESTAMP)";

        sql = "CREATE TABLE \" \" (\" cH Ange \" NUMBER(*,0), \" \" NVARCHAR2(50), \"$身份证\" TIMESTAMP, \"k.t\" TIMESTAMP WITH TIME ZONE)";

        System.out.println("sql: " + sql);

        String haha = StrUtil.subAfter(sql, "(", false);
        sql = StrUtil.subBefore(haha, ")", true);

        List<String> allField = ReUtil.findAll("\\\"(.*?)\\\"", sql, 0);

        allField.forEach(System.out::println);

        String sqlCopy = new String(sql);

        for (String s : allField) {
            sqlCopy = sqlCopy.replace(s, "");
        }

        System.out.println("sqlCopy: " + sqlCopy);

        List<String> all = ReUtil.findAll("\\((.*?)\\)", sqlCopy, 0);

        for (String s : all) {
            sqlCopy = sqlCopy.replace(s, "");
        }

        System.out.println("sqlCopy: " + sqlCopy);

        List<String> type = StrUtil.splitTrim(sqlCopy, ",");
        type.forEach(System.out::println);

        System.out.println("=====================");
        for (int i = 0; i < allField.size(); i++) {
            System.out.println(allField.get(i) + "类型：" + type.get(i));
        }

    }
}
