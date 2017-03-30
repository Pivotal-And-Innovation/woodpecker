package com.woopecker.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * 需更新节点信息传输模型
 *
 * @author Glenn
 * @since 2017-03-25
 */
@Getter
@Setter
public class TagInfoDTO implements Serializable {

    private static final long serialVersionUID = 1234253234363464575L;
    /**
     * 要更新的标签信息类型:
     * 手机号-contactNum
     * 身份证-idNum
     */
    private String tagType;
    /**
     * 节点唯一索引名,如手机号,身份证,全部是number
     */
    private String uniqueName;
    /**
     * 标签信息:{"tag1":"value","tag2":"value2"}
     */
    private Map<String, Object> tagInfo;
}
