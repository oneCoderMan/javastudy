package com.java.study.middleware.es;

import com.java.study.middleware.es.entity.GoodSearchResultVO;
import com.java.study.middleware.es.entity.GoodsSearchQry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author： yijun
 * @DATE: 2025/3/19 23:29
 * @Description 这里是使用的客户端测试
 */
@Service
public class EsClientTest {
    @Resource
    private Map<String, SearchAdaptor<GoodsSearchQry, GoodSearchResultVO>> searchAdaptorMaps;

    public void testEsQuery() {
        String serviceName = "goodsSearchAdaptor";
        GoodsSearchQry goodsSearchQry = new GoodsSearchQry();
        SearchAdaptor<GoodsSearchQry, GoodSearchResultVO> goodsSearchQryAdaptor =
                searchAdaptorMaps.get(serviceName);
        GoodSearchResultVO query = goodsSearchQryAdaptor.query(goodsSearchQry);
    }

}
