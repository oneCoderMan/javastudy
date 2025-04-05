package com.java.study.middleware.es.entity;

import lombok.Data;

/**
 * @Author： yijun
 * @DATE: 2025/3/20 23:16
 * @Description 这个是具体的实体类
 */
@Data
public class GoodsVO {
    private String goodsName;

    private String owner;

    private Double price;

}
