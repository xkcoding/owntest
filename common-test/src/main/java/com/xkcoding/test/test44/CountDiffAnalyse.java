package com.xkcoding.test.test44;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 数量比对分析
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/12 17:38
 */
@Data
@AllArgsConstructor
public class CountDiffAnalyse {
    private String startIp;
    private String endIp;

    private Integer countA;
    private Integer countB;
}
