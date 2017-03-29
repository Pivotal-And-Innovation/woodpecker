package com.woodpecker.mapper;

import com.woodpecker.entity.WoodLocationInfo;

/**
 * 地理位置信息mapper代理
 *
 * @author Glenn
 * @since 2017-03-25
 */
public interface WoodLocationInfoMapper {
    /**
     * 根据唯一索引插入记录，存在则忽略，反之则插入
     *
     * @param locationInfo 待插入记录
     * @return 1-插入成功 0-插入失败
     */
    int insertInSingle(WoodLocationInfo locationInfo);
}
