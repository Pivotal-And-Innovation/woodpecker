package com.woopecker;

import com.woopecker.DTO.TagInfoDTO;

import java.util.List;

/**
 * 用户标签信息处理接口
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public interface GraphTagInfoHandler {
    /**
     * 单条更新标签信息
     *
     * @param infoDTO 信息传输对象
     * @return 成功处理数量
     */
    int handleInSingle(TagInfoDTO infoDTO);

    /**
     * 批量更新标签信息
     *
     * @param infoDTOList 批量信息
     * @return 成功处理数量
     */
    int handleInBatch(List<TagInfoDTO> infoDTOList);
}
