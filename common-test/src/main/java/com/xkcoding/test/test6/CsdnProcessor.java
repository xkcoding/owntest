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
        // 打印页面内容
        // System.out.println(page.getHtml().toString());

        // 获取当前页面所有URL
        // System.out.println(page.getHtml().links().all());

        // 根据正则表达式获取当前页指定URL
        // System.out.println(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}").all());

        // 添加目标地址
        page.addTargetRequests(page.getHtml()
                .links()
                .regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{8}")
                .all());
        // 添加自定义变量到page对象
        page.putField("title", page.getHtml()
                .xpath("//*[@id=\"mainBox\"]/main/div[1]/div[1]/div/div[1]/h1")
                .toString());

    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

}
