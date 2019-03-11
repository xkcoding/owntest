package com.xkcoding.demo.service;

import com.xkcoding.common.api.R;
import com.xkcoding.log.model.LogApi;
import com.xkcoding.log.service.impl.DefaultScaffoldLogServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义日志记录操作
 * </p>
 *
 * @package: com.xkcoding.demo.service
 * @description: 自定义日志记录操作
 * @author: yangkai.shen
 * @date: Created in 2019-03-11 10:36
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class LogServiceImpl extends DefaultScaffoldLogServiceImpl {
    @Override
    public R<Boolean> saveApiLog(LogApi logApi) {
        log.info("【logApi】= {}", logApi);
        return super.saveApiLog(logApi);
    }
}
