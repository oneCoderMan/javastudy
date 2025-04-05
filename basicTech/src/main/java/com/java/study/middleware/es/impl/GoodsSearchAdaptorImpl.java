package com.java.study.middleware.es.impl;

import com.java.study.middleware.es.AbstractSearchAdaptor;
import com.java.study.middleware.es.entity.GoodSearchResultVO;
import com.java.study.middleware.es.entity.GoodsSearchQry;
import com.java.study.middleware.es.entity.GoodsVO;
import com.java.study.middleware.es.entity.PageVO;
import com.java.study.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： yijun
 * @DATE: 2025/3/19 23:28
 * @Description 这里就是不同业务的实现了 (可以用不同的子类做不同的查询)
 */
@Service("goodsSearchAdaptor")
@Slf4j
public class GoodsSearchAdaptorImpl
        extends AbstractSearchAdaptor<GoodsSearchQry, GoodSearchResultVO> {
    /**
     * 这里定义需要查询的索引
     */
    private static final String GOODS_INDEX = "goods_test";

    @Override
    public void validate(GoodsSearchQry data) {
        if (data == null) {
            throw new RuntimeException("qry is null");
        }
        // todo 其它校验

    }

    /**
     * 这里就是构造具体的查询请求了
     * @param searchQry
     * @return
     */
    @Override
    public SearchRequest buildSearchRequest(GoodsSearchQry searchQry) {
        SearchSourceBuilder sb = new SearchSourceBuilder();

        // 构建查询参数
        QueryBuilder boolQueryBuild = buildMainSearchQueryBuilder(searchQry);

        // 设置排序
        handleSort(searchQry, sb);

        // 设置高亮
        handleHighlighter(searchQry, sb);

        // 分页
        handlePaging(searchQry, sb);

        sb.query(boolQueryBuild);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(GOODS_INDEX);
        searchRequest.source(sb);
        return searchRequest;
    }




    /**
     * 这里构造具体的查询请求
     * @param searchQry
     * @return
     */
    private QueryBuilder buildMainSearchQueryBuilder(GoodsSearchQry searchQry) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.isNotBlank(searchQry.getName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("name", searchQry.getName()));
        }
        // todo 其它复杂条件
        return boolQueryBuilder;
    }

    private void handleSort(GoodsSearchQry searchQry,
                          SearchSourceBuilder sb) {
        // 这个排序字段可以从 searchQry 里面指定，也可以用默认的
        sb.sort("name", SortOrder.DESC);
    }

    private void handleHighlighter(GoodsSearchQry searchQry,
                                   SearchSourceBuilder sb) {

    }

    private void handlePaging(GoodsSearchQry searchQry,
                              SearchSourceBuilder sb) {
        PageVO pageVO = searchQry.getPageVO();
        Long from = (pageVO.getPage() - 1) * pageVO.getPageSize();
        sb.from(from.intValue())
                .size(pageVO.getPageSize().intValue())
                .explain(false)
                .trackTotalHits(true);
    }

    /**
     * 这里就是具体的结果转换
     * @param searchQry
     * @param response
     * @return
     */
    @Override
    public GoodSearchResultVO handleSearchResponse(GoodsSearchQry searchQry,
                                                   SearchResponse response) {
        if (response == null) {
            return null;
        }
        GoodSearchResultVO goodSearchResultVO = new GoodSearchResultVO();
        List<GoodsVO> dataList = new ArrayList<>();

        PageVO pageVO = handlePage(searchQry, response);
        // 处理结果
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            // todo 反序列化 加上try catch
            GoodsVO goodsVO = JsonUtil.fromJson(sourceAsString, GoodsVO.class);
            dataList.add(goodsVO);

            // todo 处理高亮信息，如果有
        }

        goodSearchResultVO.setDataList(dataList);
        goodSearchResultVO.setPageVO(pageVO);
        return goodSearchResultVO;
    }

    private PageVO handlePage(GoodsSearchQry searchQry,
                              SearchResponse response) {
        long value = response.getHits().getTotalHits().value;
        PageVO pageVO = new PageVO();
        pageVO.setPage(searchQry.getPageVO().getPage());
        pageVO.setPageSize(searchQry.getPageVO().getPageSize());
        pageVO.setTotalCount(value);
        return pageVO;
    }
}
