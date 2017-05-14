package com.woodpecker.mapper;

import com.woodpecker.entity.WoodUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息mapper代理
 *
 * @author Relax
 * @since 2017年03月30日
 */
public interface WoodUserMapper {
    /**
     * 根据唯一索引插入记录，存在则忽略，反之则插入
     *
     * @param user 待插入记录
     * @return 1-插入成功 0-插入失败
     */
    int insertInSingle(@Param("user") WoodUser user);

    /**
     * 根据唯一索引批量插入记录，存在则忽略，反之则插入
     *
     * @param userList 用户列表
     * @return 成功插入数
     */
    int insertInBatch(@Param("userList") List<WoodUser> userList);
}
