package com.xkcoding.springboottest.controller.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.xkcoding.springboottest.entity.easypoi.ExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 测试EasyPOI
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.easypoi
 * @description: 测试EasyPOI
 * @author: yangkai.shen
 * @date: Created in 2018-12-04 10:54
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/easypoi")
@Slf4j
public class EasyPoiController {
    /**
     * 测试读取
     *
     * @param file 文件
     * @throws IOException IO异常
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") MultipartFile file) throws Exception {
        List<ExcelModel> models = ExcelImportUtil.importExcel(file.getInputStream(), ExcelModel.class, new ImportParams());
        log.info(JSONUtil.toJsonStr(models));
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
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), ExcelModel.class, data);
        workbook.write(response.getOutputStream());
    }
}
