package com.xkcoding.test.test7;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * <p>
 * 解析 https://xclient.info
 * </p>
 *
 * @package: com.xkcoding.test.test7
 * @description: 解析 https://xclient.info
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 09:14
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class XclientProcessor implements PageProcessor {
    /**
     * process the page, extract urls to fetch, extract the data and store
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().xpath("//*[@id=\"body\"]/div[1]/div[2]/a").links().all());
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"versions\"]/table").links().all());
    }

    /**
     * get the site settings
     *
     * @return site
     * @see Site
     */
    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }
}
