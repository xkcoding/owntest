package com.xkcoding.test.test15;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.ds.hikari.HikariDSFactory;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 测试数据库表/字段信息获取
 * </p>
 *
 * @package: com.xkcoding.test.test15
 * @description: 测试数据库表/字段信息获取
 * @author: yangkai.shen
 * @date: Created in 2019-01-30 09:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test15 {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = initDatasource();
        tables(dataSource);
        tables(dataSource, "sys_user", "sys_token");
        columns(dataSource,"sys_user");
    }

    private static DataSource initDatasource() {
        Setting setting = new Setting();
        setting.put("url", "jdbc:mysql://192.168.241.12:3306/data_manager?useUnicode=true&characterEncoding=utf8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8");
        setting.put("username", "root");
        setting.put("password", "root");
        setting.put("driver", "com.mysql.cj.jdbc.Driver");
        setting.put("showSql", "true");
        setting.put("formatSql", "true");
        setting.put("showParams", "true");

        return new HikariDSFactory(setting).getDataSource();
    }

    public static void tables(DataSource dataSource, String... tableName) throws SQLException {
        String append = "";
        if (ArrayUtil.isNotEmpty(tableName)) {
            String params = StrUtil.repeatAndJoin("?", ArrayUtil.length(tableName), ",");
            append = " and table_name in (" + params + ")";
        }
        String sql = "select table_name tableName, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database())" + (StrUtil
                .isNotBlank(append) ? append : "") + " order by create_time asc";

        List<TableInfo> query = Db.use(dataSource)
                .query(sql, TableInfo.class, StrUtil.isNotBlank(append) ? tableName : new Object[]{});
        log.info("【query】= {}", JSONUtil.toJsonStr(query));
    }

    public static void columns(DataSource dataSource, String tableName) throws SQLException {
        String sql = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position";

        List<ColumnInfo> query = Db.use(dataSource).query(sql, ColumnInfo.class, tableName);
        log.info("【query】= {}", JSONUtil.toJsonStr(query));
    }
}
