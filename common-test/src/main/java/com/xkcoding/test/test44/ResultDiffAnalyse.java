package com.xkcoding.test.test44;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 结果比对分析
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/12 17:42
 */
@Data
@AllArgsConstructor
public class ResultDiffAnalyse {
    private String ip;
    private String port;

    private String type;
    private Boolean status;

    private Boolean belongA;
    private Boolean belongB;
}
