package com.java.study.designpattern.somtech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 21:28
 * @Description 消除多余的if-else方法
 */
@Service
@Slf4j
public class IfReplace {
    @Autowired
    private OptStrategyContext optStrategyContext;

    /**
     * 客户端使用代码
     * @param args
     */
    public void test(String[] args) {
        int devideOpt = optStrategyContext.apply("devideOpt", 4, 2);
        log.info("{}", devideOpt);
    }
}
