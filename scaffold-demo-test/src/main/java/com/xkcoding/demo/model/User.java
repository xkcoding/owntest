package com.xkcoding.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户测试实体类
 * </p>
 *
 * @package: com.xkcoding.demo.model
 * @description: 用户测试实体类
 * @author: yangkai.shen
 * @date: Created in 2019-04-08 10:22
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@ApiModel(description = "用户信息")
public class User {
    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "邮箱")
    private String email;
}