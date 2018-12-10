package com.xkcoding.test.test7;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * <p>
 * 测试 https://xclient.info
 * </p>
 *
 * @package: com.xkcoding.test.test7
 * @description: 测试 https://xclient.info
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 09:13
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test7 {
    public static void main(String[] args) {
        Spider.create(new XclientProcessor())
                .addUrl("https://xclient.info/s/istatistica.html")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .setDownloader(new PhantomJSDownloader("/usr/local/bin/phantomjs", "/Users/yangkai.shen/Desktop/crawl.js"))
                .run();
    }
}
