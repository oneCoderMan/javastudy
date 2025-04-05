package com.java.study.middleware.es;

import lombok.Getter;

/**
 * @Author： yijun
 * @DATE: 2025/3/19 23:54
 * @Description
 */
@Getter
public enum ElasticClientNameEnum {
    GOODS_READ("goodsReadEsClient", "用于物品服务的ES读"),
    GOODS_WRITE("goodsWriteEsClient", "用于物品服务的ES写"),
    ;
    private String esServiceName;
    private String desc;
    ElasticClientNameEnum(String esServiceName, String desc) {
        this.esServiceName  = esServiceName;
        this.desc = desc;
    }
}
