package com.java.study.middleware.es.impl;

import com.java.study.middleware.es.AbstractSearchAdaptor;
import com.java.study.middleware.es.entity.GoodSearchResultVO;
import com.java.study.middleware.es.entity.GoodsSearchQry;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.stereotype.Service;

/**
 * @Author： yijun
 * @DATE: 2025/3/20 23:55
 * @Description
 */
@Service("goodsSearchContainAdaptor")
@Slf4j
public class GoodsSearchContainAdaptorImpl
        extends AbstractSearchAdaptor<GoodsSearchQry, GoodSearchResultVO> {
    @Override
    public void validate(GoodsSearchQry data) {

    }

    @Override
    public SearchRequest buildSearchRequest(GoodsSearchQry searchQry) {
        return null;
    }

    @Override
    public GoodSearchResultVO handleSearchResponse(GoodsSearchQry searchQry, SearchResponse response) {
        return null;
    }
}
