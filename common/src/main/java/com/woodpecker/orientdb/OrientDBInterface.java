package com.woodpecker.orientdb;

import com.woodpecker.orientdb.entity.EdgeDefinition;
import com.woodpecker.orientdb.entity.VertexDefinition;

/**
 * orientDB图库对接接口
 *
 * @author Glenn
 * @since 2017-03-30
 */
public interface OrientDBInterface {
    /**
     * 源顶点到目标顶点建立出边关系
     *
     * @param sourceVertex 源顶点
     * @param targetVertex 目标顶点
     * @return 1
     */
    int buildOutRelation(VertexDefinition sourceVertex, VertexDefinition targetVertex);

    /**
     * 创建唯一索引节点,但节点不存存在时,创建新的节点;否则更新节点
     *
     * @param vertexDefinition 顶点
     * @return 顶点recordID
     */
    String updateUniqueVertex(VertexDefinition vertexDefinition);

    /**
     * 创建唯一索引边,当边不存存在时,创建新的边;否则更新边
     *
     * @param edgeDefinition 边
     * @return 边recordID
     */
    String updateUniqueEdge(EdgeDefinition edgeDefinition);
}
