package com.xkcoding.test.test36;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>
 * 切分 JDBC URL
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-08-02 17:13
 */
@Slf4j
public class Test36 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://192.168.239.4:3306/dmcp-master?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true&remarks=true&useInformationSchema=true";

        String protocol = StrUtil.subBefore(jdbcUrl, StrUtil.COLON + StrUtil.SLASH + StrUtil.SLASH, true);
        log.info("【protocol】= {}", protocol);

        String hostAndPort = StrUtil.subBetween(jdbcUrl, StrUtil.COLON + StrUtil.SLASH + StrUtil.SLASH, StrUtil.SLASH);
        log.info("【hostAndPort】= {}", hostAndPort);

        String host = StrUtil.subBefore(hostAndPort, StrUtil.COLON, true);
        log.info("【host】= {}", host);

        String port = StrUtil.subAfter(hostAndPort, StrUtil.COLON, true);
        log.info("【port】= {}", port);

        String dbName = StrUtil.subBetween(jdbcUrl, hostAndPort + StrUtil.SLASH, "?");
        log.info("【dbName】= {}", dbName);

        String paramStr = StrUtil.subAfter(jdbcUrl, "?", true);
        Map<String, String> param = Splitter.on("&").withKeyValueSeparator("=").split(paramStr);
        log.info("【param】= {}", param);

    }
}
