package com.java.study.middleware.es.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2025/3/20 23:16
 * @Description 这个是返回结构
 */
@Data
public class GoodSearchResultVO {
    private PageVO pageVO;

    private List<GoodsVO> dataList;
}
