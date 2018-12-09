package com.xkcoding.test.test6;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * <p>
 * 自定义CSDN爬取器
 * </p>
 *
 * @package: com.xkcoding.test.test6
 * @description: 自定义CSDN爬取器
 * @author: yangkai.shen
 * @date: Created in 2018-12-09 23:17
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class CsdnProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

}
