package com.xkcoding.springboottest.entity.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 测试EasyExcel
 * </p>
 *
 * @package: com.xkcoding.springboottest.entity.easyexcel
 * @description: 测试EasyExcel
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 16:14
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class ExcelModel extends BaseRowModel {
    @ExcelProperty(value = "序号", index = 0)
    private String id;
    @ExcelProperty(value = "姓名", index = 1)
    private String name;
    @ExcelProperty(value = "生日", index = 2, format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @ExcelProperty(value = "年龄", index = 3)
    private Integer age;
}
