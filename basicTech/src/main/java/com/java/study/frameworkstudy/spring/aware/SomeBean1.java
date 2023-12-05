package com.java.study.frameworkstudy.spring.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Author： yijun
 * @DATE: 2023/12/5 08:25
 * @Description
 */
@Slf4j
public class SomeBean1 implements InitializingBean {
    @PostConstruct
    public void init() {
        log.info("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化2");
    }

    public void init3() {
        log.info("初始化3");
    }
}
