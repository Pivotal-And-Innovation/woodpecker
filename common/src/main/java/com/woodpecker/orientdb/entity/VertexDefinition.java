package com.woodpecker.orientdb.entity;

import lombok.Data;

import java.util.Map;

/**
 * 图库顶点定义实体
 *
 * @author Relax
 * @since 2017年03月30日
 */
@Data
public class VertexDefinition {
    /**
     * 顶点名
     */
    private String vertexName;
    /**
     * 顶点where条件子句
     */
    private String whereCause;
    /**
     * 顶点属性集
     */
    private Map<String, Object> properties;
    /**
     * 顶点出边
     */
    private EdgeDefinition definition;

    public VertexDefinition(String vertexName, Map<String, Object> properties, String whereCause) {
        this.vertexName = vertexName;
        this.properties = properties;
        this.whereCause = whereCause;
    }
}
