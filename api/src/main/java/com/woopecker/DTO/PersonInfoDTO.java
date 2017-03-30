package com.woopecker.DTO;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 个人信息传输模型
 *
 * @author Glenn
 * @since 2017-03-30
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class PersonInfoDTO implements Serializable {

    private static final long serialVersionUID = 8189192178516247341L;
    /**
     * 手机号
     */
    @NonNull
    private String phone;
    /**
     * 身份证号-唯一索引
     */
    @NonNull
    private String idNum;
    /**
     * 标识贷款用户的标签
     */
    private List<String> tags;
}
