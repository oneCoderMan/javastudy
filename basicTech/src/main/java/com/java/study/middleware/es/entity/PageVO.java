package com.java.study.middleware.es.entity;

import lombok.Data;

/**
 * @Author： yijun
 * @DATE: 2025/3/20 23:15
 * @Description
 */
@Data
public class PageVO {
    /**
     * 从1开始
     */
    private Long page;

    private Long pageSize;

    private Long totalCount;
}
