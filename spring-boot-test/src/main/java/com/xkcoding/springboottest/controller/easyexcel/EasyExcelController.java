package com.xkcoding.springboottest.controller.easyexcel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.xkcoding.springboottest.entity.easyexcel.ExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 测试EasyExcel
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.easyexcel
 * @description: 测试EasyExcel
 * @author: yangkai.shen
 * @date: Created in 2018-12-04 10:54
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/easyexcel")
@Slf4j
public class EasyExcelController {
    /**
     * 测试读取
     *
     * @param file 文件
     * @throws IOException IO异常
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        AnalysisEventListener listener = new ExcelListener();
        ExcelReader reader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLSX, listener);
        reader.read(new Sheet(1, 1, ExcelModel.class));
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(HttpServletResponse response) throws IOException {
        List<ExcelModel> data = Lists.newArrayList();
        data.add(new ExcelModel().setId(IdUtil.fastSimpleUUID())
                .setBirthday(DateUtil.date())
                .setAge(20)
                .setName("用户1"));
        data.add(new ExcelModel().setId(IdUtil.fastSimpleUUID())
                .setBirthday(DateUtil.yesterday())
                .setAge(20)
                .setName("用户1"));
        data.add(new ExcelModel().setId(IdUtil.fastSimpleUUID())
                .setBirthday(DateUtil.tomorrow())
                .setAge(20)
                .setName("用户1"));

        // 待生成的 excel 文件路径
        String fileName = "excel.xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        ExcelWriter writer = new ExcelWriter(response.getOutputStream(), ExcelTypeEnum.XLSX, true);
        Sheet sheet = new Sheet(1, 0, ExcelModel.class);
        writer.write(data, sheet);
        writer.finish();
    }
}
