package com.woodpecker.entity;

import lombok.Data;

import java.util.Date;

/**
 * 地理位置信息Entity
 *
 * @author Glenn
 * @since 2017-03-25
 */
@Data
@SuppressWarnings("unused")
public class WoodLocationInfo {
    /**
     * 记录ID
     */
    private int id;
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
    /**
     * 上报时间
     */
    private Date upTime;
}
