package com.java.study.middleware.es;

import com.java.study.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

import static com.java.study.middleware.es.ElasticClientNameEnum.GOODS_READ;
import static com.java.study.middleware.es.ElasticClientNameEnum.GOODS_WRITE;

/**
 * @Author： yijun
 * @DATE: 2025/3/19 23:23
 * @Description 模版模式
 */
@Slf4j
public abstract class AbstractSearchAdaptor<T, U> implements SearchAdaptor<T, U> {

    @Resource
    protected ApplicationContext applicationContext;

    protected RestHighLevelClient writeElasticClient;

    protected RestHighLevelClient readElasticClient;

    @PostConstruct
    public void init() {
        initReadClient();
        initWriteClient();
    }

    /**
     * 不同子类可以覆盖这个，从而实现使用不同的ES
     * 如果子类不覆盖，那就是使用默认的
     */
    protected void initReadClient() {
        this.writeElasticClient = applicationContext.getBean(
                GOODS_READ.getEsServiceName(), RestHighLevelClient.class);
    }


    /**
     * 不同子类可以覆盖这个，从而实现使用不同的ES
     */
    private void initWriteClient() {
        this.writeElasticClient = applicationContext.getBean(
                GOODS_WRITE.getEsServiceName(), RestHighLevelClient.class);

    }

    /**
     * 这里定义一个通用的查询模版
     * @param searchQry
     * @return
     */
    @Override
    public U query(T searchQry) {
        StopWatch stopwatch = new StopWatch();

        stopwatch.start("[AbstractSearchAdaptor query] validate");
        validate(searchQry);
        stopwatch.stop();

        // 构建查询条件
        stopwatch.start("[AbstractSearchAdaptor query] buildSearchRequest");
        SearchRequest request = buildSearchRequest(searchQry);
        stopwatch.stop();

        // 执行查询
        stopwatch.start("[AbstractSearchAdaptor query] executeSearch");
        SearchResponse response = executeSearch(request);
        stopwatch.stop();


        // 处理查询结果
        stopwatch.start("[AbstractSearchAdaptor query] handleSearchResponse");
        U result = handleSearchResponse(searchQry, response);
        stopwatch.stop();

        log.info("[AbstractSearchAdaptor query] finish--- cost:{}", stopwatch.prettyPrint());
        return result;
    }



    /**
     * 这个是一个通用的查询，所有的都是一样，不需要在子类中实现
     * @param request
     * @return
     */
    private SearchResponse executeSearch(SearchRequest request) {
        SearchResponse response;
        try {
            log.info("AbstractSearchAdaptor dsl:{}, client:{}, index:{}",
                    request.source(), JsonUtil.toJson(readElasticClient), request.indices());
            response = readElasticClient.search(request, RequestOptions.DEFAULT);
            // todo 这里可以加入其它的监控，比如慢查询记录，好使记录
        } catch (IOException e) {
            log.error("AbstractSearchAdaptor error", e);
            throw new RuntimeException(e);
        }
        return response;
    }


    /**
     * 进行参数检验
     * @param data
     */
    public abstract void validate(T data);

    /**
     * 构造查询条件，具体在子类中实现
     * @param searchQry
     * @return
     */
    public abstract SearchRequest buildSearchRequest(T searchQry);

    /**
     * 组装返回结果，具体在子类中实现
     * @param searchQry
     * @param response
     * @return
     */
    public abstract U handleSearchResponse(T searchQry, SearchResponse response);



}
