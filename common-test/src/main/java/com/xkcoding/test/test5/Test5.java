package com.xkcoding.test.test5;

import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.db.meta.MetaUtil;
import cn.hutool.db.meta.Table;
import cn.hutool.json.JSONUtil;

import java.util.List;

/**
 * <p>
 * 测试Hutool的MetaUtil
 * </p>
 *
 * @package: com.xkcoding.test.test5
 * @description: 测试Hutool的MetaUtil
 * @author: yangkai.shen
 * @date: Created in 2018-12-04 14:46
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test5 {
    public static void main(String[] args) {
        SimpleDataSource dataSource=new SimpleDataSource("jdbc:mysql://127.0.0.1:3306/spring-boot-demo?useInformationSchema=true", "root", "root");
        List<String> tables = MetaUtil.getTables(dataSource);
        System.out.println(tables);
        for (String table : tables) {
            Table tableMeta = MetaUtil.getTableMeta(dataSource, table);
            System.out.println(JSONUtil.toJsonStr(tableMeta));
        }
    }
}
