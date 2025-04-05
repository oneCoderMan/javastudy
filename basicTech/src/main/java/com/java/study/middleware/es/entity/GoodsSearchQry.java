package com.java.study.middleware.es.entity;

import lombok.Data;

/**
 * @Author： yijun
 * @DATE: 2025/3/20 23:13
 * @Description
 */
@Data
public class GoodsSearchQry {
    private PageVO pageVO;

    private String name;

    private String price;

    // todo 其它字段
}
