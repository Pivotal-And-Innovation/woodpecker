package com.woodpecker.api;

import com.woopecker.DTO.TagInfoDTO;
import com.woopecker.GraphTagInfoHandler;

import java.util.List;

/**
 * {@code GraphTagInfoHandler} 具体实现
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
public class GraphTagInfoHandlerImpl implements GraphTagInfoHandler {

    /**
     * 单条更新标签信息
     *
     * @param infoDTO 信息传输对象
     * @return 成功处理数量
     */
    @Override
    public int handleInSingle(TagInfoDTO infoDTO) {
        return 0;
    }

    /**
     * 批量更新标签信息
     *
     * @param infoDTOList 批量信息
     * @return 成功处理数量
     */
    @Override
    public int handleInBatch(List<TagInfoDTO> infoDTOList) {
        return 0;
    }
}
