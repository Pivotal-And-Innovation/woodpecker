package com.woodpecker.api;

import com.woopecker.DTO.PersonInfoDTO;
import com.woopecker.GraphPersonInfoHandler;

import java.util.List;
import java.util.Map;

/**
 * {@code GraphPersonInfoHandler} 具体实现
 *
 * @author Glenn
 * @since 2017-03-30
 */
public class GraphPersonInfoHandlerImpl implements GraphPersonInfoHandler {
    /**
     * 单笔获取贷款关联用户数
     *
     * @param infoDTO 用户信息
     * @return 关联贷款的用户数
     */
    @Override
    public int getLinkNumInSingle(PersonInfoDTO infoDTO) {
        return 0;
    }

    /**
     * 批量获取贷款关联用户数
     *
     * @param infoDTOList 用户信息列表
     * @return key-手机号, value-关联的贷款用户数
     */
    @Override
    public Map<String, Integer> getLinkNumInBatch(List<PersonInfoDTO> infoDTOList) {
        return null;
    }
}
