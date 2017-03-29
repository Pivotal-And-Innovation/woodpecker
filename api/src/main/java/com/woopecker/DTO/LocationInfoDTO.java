package com.woopecker.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * 地理位置信息模型类
 *
 * @author Glenn
 * @since 2017-03-25
 */
@Data
@SuppressWarnings("unused")
public class LocationInfoDTO implements Serializable {

    private static final long serialVersionUID = 1234253234363464575L;

    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idNum;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

}
