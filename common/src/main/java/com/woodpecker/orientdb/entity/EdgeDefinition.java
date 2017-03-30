package com.woodpecker.orientdb.entity;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.woodpecker.util.MD5;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * 图库边定义实体
 *
 * @author Glenn
 * @since 2017-03-30
 */
@Setter
@Getter
public class EdgeDefinition {
    /**
     * 边名称
     */
    private String edgeName;
    /**
     * 标识边的唯一索引, 格式:MD5(vertex1.unique_vertex2.unique)
     */
    private String uniqueName;
    /**
     * 边属性集
     */
    private Map<String, Object> content;
    /**
     * 边时间戳：用于标识边的更新时间，防止逆向更新
     */
    private long timeStamp;

    public EdgeDefinition(String edgeName, Map<String, Object> content) {
        this.edgeName = edgeName;
        this.content = content;
    }

    public EdgeDefinition(String edgeName, String firstUnique, String secondUnique, Date lastTime) {
        this.edgeName = edgeName;
        this.uniqueName = MD5.encrypt(firstUnique + "_" + secondUnique);
        this.content = Maps.newHashMap();
        this.timeStamp = lastTime.getTime();
        content.put("uniqueName", uniqueName);
        content.put("updateTime",timeStamp);
    }

    public String getContent() {
        if (content == null) {
            return "";
        }
        return " content " + JSON.toJSON(content);
    }

}
