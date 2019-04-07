package com.xkcoding.demo.service;

import com.xkcoding.scaffold.common.api.R;
import com.xkcoding.scaffold.log.model.LogApi;
import com.xkcoding.scaffold.log.model.LogCustom;
import com.xkcoding.scaffold.log.model.LogError;
import com.xkcoding.scaffold.log.service.impl.DefaultScaffoldLogServiceImpl;

/**
 * <p>
 * 日志实现
 * </p>
 *
 * @package: com.xkcoding.demo.service
 * @description: 日志实现
 * @author: yangkai.shen
 * @date: Created in 2019-03-13 14:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class CustomLogServiceImpl extends DefaultScaffoldLogServiceImpl {
    /**
     * 保存操作日志
     *
     * @param logApi 操作日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveApiLog(LogApi logApi) {
        return super.saveApiLog(logApi);
    }

    /**
     * 保存错误日志
     *
     * @param logError 错误日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveErrorLog(LogError logError) {
        return super.saveErrorLog(logError);
    }

    /**
     * 保存自定义日志
     *
     * @param logCustom 自定义日志实体
     * @return 是否保存成功
     */
    @Override
    public R<Boolean> saveCustomLog(LogCustom logCustom) {
        return super.saveCustomLog(logCustom);
    }
}
