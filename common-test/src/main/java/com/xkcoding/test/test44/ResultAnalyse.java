package com.xkcoding.test.test44;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 扫描结果对象
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020/2/12 17:29
 */
@Data
@AllArgsConstructor
public class ResultAnalyse {
    private String taskId;
    private String taskName;

    private List<String> ipRange = new ArrayList<>();
    private String startTime;

    private Integer validNum;
    private Integer dbCount;
}
