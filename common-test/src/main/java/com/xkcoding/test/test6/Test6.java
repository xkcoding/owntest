package com.xkcoding.test.test6;

import us.codecraft.webmagic.Spider;

/**
 * <p>
 * 测试webmagic爬取https://blog.csdn.net/
 * </p>
 *
 * @package: com.xkcoding.test.test6
 * @description: 测试webmagic爬取https://blog.csdn.net/
 * @author: yangkai.shen
 * @date: Created in 2018-12-09 23:14
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test6 {
    public static void main(String[] args) {
        Spider.create(new CsdnProcessor()).addUrl("https://blog.csdn.net/").run();
    }
}
