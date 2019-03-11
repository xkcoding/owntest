package com.xkcoding.test.test17;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 测试hutool监听文件修改事件
 * </p>
 *
 * @package: com.xkcoding.test.test17
 * @description: 测试hutool监听文件修改事件
 * @author: yangkai.shen
 * @date: Created in 2019-03-06 09:49
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class Test17 {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("dblist.result");
        File file = resource.getFile();
        final List<String>[] initLines = new List[]{FileUtil.readLines(file, Charsets.UTF_8)};
        log.info("读取到文件数据【initLines】= {}", initLines[0]);
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {

            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                // Object obj = event.context();
                // log.info("修改：{}-> {}", currentPath, obj);
                log.info("初始化【initLines】= {}", initLines[0]);
                List<String> modifiedLines = FileUtil.readLines(file, Charsets.UTF_8);
                log.info("修改后【modifiedLines】= {}", modifiedLines);
                Collection<String> disjunction = CollUtil.disjunction(initLines[0], modifiedLines);
                for (String item : disjunction) {
                    String logger = CollUtil.contains(modifiedLines, item) ? "新增的：{}" : "删除的：{}";
                    log.info(logger, item);
                }
                // 修改后的覆盖初始值
                initLines[0] = modifiedLines;
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {

            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {

            }
        });
        watchMonitor.start();
    }
}
