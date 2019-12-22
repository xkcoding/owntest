package com.xkcoding.test.test41;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 测试 EasyPOI
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/12/22 10:03
 */
@UtilityClass
public class Test41 {
    public static void main(String[] args) {
        // 测试导出简单文件
        // exportSimpleExcel();

        // 模拟大数据量分页导出
        // exportPageExcel();

        // 模拟模板导出
        // exportTemplateExcel();

        // 测试表头样式
        exportStyleExcel();
    }

    @SneakyThrows
    public void exportStyleExcel() {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("序号", "index"));
        entity.add(new ExcelExportEntity("姓名", "name"));
        entity.add(new ExcelExportEntity("性别", "sex"));

        ExportParams exportParams = new ExportParams("大数据量测试标题", "测试 sheet");
        exportParams.setStyle(ExcelExportStyler.class);
        Workbook excel = ExcelExportUtil.exportBigExcel(exportParams, entity, pageServer(4), null);

        try (FileOutputStream fos = new FileOutputStream("/Users/yangkai.shen/Desktop/big_excel_styles.xlsx")) {
            excel.write(fos);
        }
    }

    @SneakyThrows
    public void exportTemplateExcel() {
        TemplateExportParams params = new TemplateExportParams("/Users/yangkai.shen/Desktop/template_excel.xlsx");

        List<Object> dataSet = new ArrayList<>();
        mockData(dataSet, 1);
        Map<String, Object> data = new HashMap<>(1);
        data.put("data", dataSet);

        Workbook excel = ExcelExportUtil.exportExcel(params, data);

        try (FileOutputStream fos = new FileOutputStream("/Users/yangkai.shen/Desktop/template_excel_1.xlsx")) {
            excel.write(fos);
        }

    }

    @SneakyThrows
    public void exportPageExcel() {
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("序号", "index"));
        entity.add(new ExcelExportEntity("姓名", "name"));
        entity.add(new ExcelExportEntity("性别", "sex"));


        Workbook excel = ExcelExportUtil.exportBigExcel(new ExportParams("大数据量测试标题", "测试 sheet"), entity, pageServer(4), null);

        try (FileOutputStream fos = new FileOutputStream("/Users/yangkai.shen/Desktop/big_excel.xlsx")) {
            excel.write(fos);
        }
    }

    @SneakyThrows
    public void exportSimpleExcel() {
        List<Object> dataSet = new ArrayList<>();
        mockData(dataSet, 1);

        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("序号", "index"));
        entity.add(new ExcelExportEntity("姓名", "name"));
        entity.add(new ExcelExportEntity("性别", "sex"));

        Workbook excel = ExcelExportUtil.exportExcel(new ExportParams("测试标题", "测试 sheet", ExcelType.XSSF), entity, dataSet);

        try (FileOutputStream fos = new FileOutputStream("/Users/yangkai.shen/Desktop/simple_excel.xlsx")) {
            excel.write(fos);
        }
    }

    public void mockData(List<Object> dataSet, Integer page) {
        for (int i = 0; i < 10; i++) {
            Map<String, Object> man = new HashMap<>(2);
            man.put("index", (page - 1) * 10 + (i + 1));
            man.put("name", "操作人员" + ((page - 1) * 10 + (i + 1)));
            man.put("sex", (i + 1) % 2);
            dataSet.add(man);
        }
    }

    public IExcelExportServer pageServer(int maxPage) {
        return (queryParams, page) -> {
            if (page > maxPage) {
                return null;
            } else {
                List<Object> dataSet = new ArrayList<>();
                mockData(dataSet, page);
                return dataSet;
            }
        };
    }

}
