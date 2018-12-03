package com.xkcoding.springboottest.entity.iexcel;

import com.github.houbb.iexcel.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 测试iexcel
 * </p>
 *
 * @package: com.xkcoding.springboottest.entity.iexcel
 * @description: 测试iexcel
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
    @ExcelField(headName = "序号")
    private String id;
    @ExcelField(headName = "姓名")
    private String name;
    @ExcelField(headName = "生日")
    private Date birthday;
    @ExcelField(headName = "年龄")
    private Integer age;
}
