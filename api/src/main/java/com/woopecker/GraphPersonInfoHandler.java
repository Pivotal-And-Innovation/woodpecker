package com.woopecker;

import com.woopecker.DTO.PersonInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * 用户个人信息处理接口
 *
 * @author Relax
 * @since 2017年03月30日
 */
public interface GraphPersonInfoHandler {
    /**
     * 单笔获取贷款关联用户数
     *
     * @param infoDTO 用户信息
     * @return 关联贷款的用户数
     */
    int getLinkNumInSingle(PersonInfoDTO infoDTO);

    /**
     * 批量获取贷款关联用户数
     *
     * @param infoDTOList 用户信息列表
     * @return key-手机号, value-关联的贷款用户数
     */
    Map<String, Integer> getLinkNumInBatch(List<PersonInfoDTO> infoDTOList);
}
