package com.xkcoding.springboottest.entity.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 测试EasyPOI
 * </p>
 *
 * @package: com.xkcoding.springboottest.entity.easypoi
 * @description: 测试EasyPOI
 * @author: yangkai.shen
 * @date: Created in 2018-12-03 16:14
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class ExcelModel {
    @Excel(name = "序号", width = 25)
    private String id;
    @Excel(name = "姓名", orderNum = "1")
    private String name;
    @Excel(name = "生日", orderNum = "2", format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @Excel(name = "年龄", orderNum = "3")
    private Integer age;
}
