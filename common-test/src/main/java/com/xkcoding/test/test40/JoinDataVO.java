package com.xkcoding.test.test40;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * 管理对象 VO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019/11/27 17:18
 */
@Data
@Document(value = "join_data")
@NoArgsConstructor
@AllArgsConstructor
public class JoinDataVO {
    @Id
    private Long id;
    private Long sourceId;
    private String key;
    private String value;
    private String relation;
}
