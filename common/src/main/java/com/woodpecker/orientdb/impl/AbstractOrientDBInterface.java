package com.woodpecker.orientdb.impl;

import com.woodpecker.orientdb.OrientDBInterface;
import com.woodpecker.orientdb.entity.EdgeDefinition;
import com.woodpecker.orientdb.entity.VertexDefinition;

/**
 * {@code OrientDBInterface} 骨架实现
 *
 * @author Glenn
 * @since 2017-03-30
 */
public class AbstractOrientDBInterface implements OrientDBInterface {
//    private OrientGraphFactory orientGraphFactory;
//    public void setOrientGraphFactory(OrientGraphFactory orientGraphFactory) {
//        this.orientGraphFactory = orientGraphFactory;
//    }

    /**
     * 源顶点到目标顶点建立出边关系
     *
     * @param sourceVertex 源顶点
     * @param targetVertex 目标顶点
     * @return 1
     */
    @Override
    public int buildOutRelation(VertexDefinition sourceVertex, VertexDefinition targetVertex) {
        return 0;
    }

    /**
     * 创建唯一索引节点,但节点不存存在时,创建新的节点;否则更新节点
     *
     * @param vertexDefinition 顶点
     * @return 顶点recordID
     */
    @Override
    public String updateUniqueVertex(VertexDefinition vertexDefinition) {
        return null;
    }

    /**
     * 创建唯一索引边,当边不存存在时,创建新的边;否则更新边
     *
     * @param edgeDefinition 边
     * @return 边recordID
     */
    @Override
    public String updateUniqueEdge(EdgeDefinition edgeDefinition) {
        return null;
    }
}
