package com.xkcoding.test.test13.case2;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 订单事件
 * </p>
 *
 * @package: com.xkcoding.test.test13
 * @description: 订单事件
 * @author: yangkai.shen
 * @date: Created in 2019-01-29 10:39
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class OrderEvent {
    /**
     * 订单编号
     */
    private Long id;
    /**
     * 订单金额
     */
    private BigDecimal price;
}
