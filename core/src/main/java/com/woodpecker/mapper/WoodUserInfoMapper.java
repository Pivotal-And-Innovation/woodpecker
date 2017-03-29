package com.woodpecker.mapper;

import com.woodpecker.entity.WoodUserInfo;

/**
 * 用户信息mapper代理
 *
 * @author Glenn
 * @since 2017-03-27
 */
public interface WoodUserInfoMapper {
    /**
     * 根据唯一索引插入记录，存在则忽略，反之则插入
     *
     * @param userInfo 待插入记录
     * @return 1-插入成功 0-插入失败
     */
    int insertInSingle(WoodUserInfo userInfo);
}
