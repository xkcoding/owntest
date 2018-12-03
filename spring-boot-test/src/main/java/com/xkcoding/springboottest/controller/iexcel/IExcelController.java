package com.xkcoding.springboottest.controller.iexcel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.github.houbb.iexcel.core.reader.IExcelReader;
import com.github.houbb.iexcel.core.writer.IExcelWriter;
import com.github.houbb.iexcel.exception.ExcelRuntimeException;
import com.github.houbb.iexcel.util.excel.ExcelUtil;
import com.google.common.collect.Lists;
import com.xkcoding.springboottest.entity.iexcel.ExcelModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 测试iexcel
 * </p>
 *
 * @package: com.xkcoding.springboottest.controller.iexcel
 * @description: 测试iexcel
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 16:13
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/iexcel")
@Slf4j
public class IExcelController {
    /**
     * 测试读取
     *
     * @param file 文件
     * @throws IOException IO异常
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        File excel = FileUtil.touch("/Users/yangkai.shen/Desktop" + file.getOriginalFilename());
        IExcelReader<ExcelModel> reader = ExcelUtil.getBigExcelReader(excel);
        List<ExcelModel> list = reader.readAll(ExcelModel.class);
        log.info("{}", JSONUtil.toJsonStr(list));
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(HttpServletResponse response) {
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
        String fileName = "excel.xls";

        try (IExcelWriter excelWriter = ExcelUtil.get07ExcelWriter(); OutputStream outputStream = response.getOutputStream()) {
            // 可根据实际需要，多次写入列表
            excelWriter.write(data);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            // 将列表内容真正的输出到 excel 文件
            excelWriter.flush(outputStream);
        } catch (IOException e) {
            throw new ExcelRuntimeException(e);
        }

    }
}
