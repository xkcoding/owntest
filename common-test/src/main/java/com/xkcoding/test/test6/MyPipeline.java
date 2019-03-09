package com.xkcoding.test.test6;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * <p>
 * 自定义PipeLine
 * </p>
 *
 * @package: com.xkcoding.test.test6
 * @description: 自定义PipeLine
 * @author: yangkai.shen
 * @date: Created in 2018-12-09 23:35
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class MyPipeline implements Pipeline {
    /**
     * Process extracted results.
     *
     * @param resultItems resultItems
     * @param task        task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        System.out.println("title = " + title);
    }
}
