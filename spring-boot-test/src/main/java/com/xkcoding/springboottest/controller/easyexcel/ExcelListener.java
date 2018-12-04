package com.xkcoding.springboottest.controller.easyexcel;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xkcoding.springboottest.entity.easyexcel.ExcelModel;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 解析监听器，
 * 每解析一行会回调invoke()方法，
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.easyexcel
 * @description: 解析监听器，每解析一行会回调invoke()方法，整个excel解析结束会执行doAfterAllAnalysed()方法
 * @author: yangkai.shen
 * @date: Created in 2018-12-04 10:58
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<ExcelModel> {
    /**
     * 每行解析处理回调
     *
     * @param object  一行数据
     * @param context 上下文
     */
    @Override
    public void invoke(ExcelModel object, AnalysisContext context) {
        log.info("当前行：" + context.getCurrentRowNum());
        log.info(JSONUtil.toJsonStr(object));
        //根据自己业务做处理
        doSomething(object);
    }

    private void doSomething(ExcelModel object) {
    }

    /**
     * 全部解析完成处理
     *
     * @param context 上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("全部处理完成");
    }
}
