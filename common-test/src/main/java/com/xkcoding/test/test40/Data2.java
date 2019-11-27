package com.xkcoding.test.test40;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 测试表结构 2
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/11/27 10:22
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Data2 {
    @Id
    private Long id;
    @Indexed
    private String key;
    private String value;
}
