package com.woodpecker.service;

import com.woodpecker.entity.WoodUser;
import com.woodpecker.mapper.WoodUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 用户服务层
 *
 * @author Relax
 * @since 2017年03月30日
 */
@Service
public class WoodUserService {
    @Resource
    private WoodUserMapper woodUserMapper;

    /**
     * 根据唯一索引插入记录，存在则忽略，反之则插入
     *
     * @param user 待插入记录
     * @return 1-插入成功 0-插入失败
     */
    public int insertInSingle(WoodUser user) {
        return woodUserMapper.insertInSingle(user);
    }

    /**
     * 根据唯一索引批量插入记录，存在则忽略，反之则插入
     *
     * @param userList 用户列表
     * @return 成功插入数
     */
    public int insertInBatch(List<WoodUser> userList) {
        return woodUserMapper.insertInBatch(userList);
    }

}
