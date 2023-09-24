package com.java.study.designpattern.somtech;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 21:43
 * @Description
 * [参考文章]
 * https://pdai.tech/md/develop/refactor/dev-refactor-if-else.html
 */
@Component
public class OptStrategyContext {
    @Resource
    private Map<String, Opt> strategyMap;

    public int apply(String opt, int a, int b) {
        return strategyMap.get(opt).apply(a, b);
    }
}
