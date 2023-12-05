package com.java.study.frameworkstudy.spring.aware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @Author： yijun
 * @DATE: 2023/12/5 08:25
 * @Description
 */
@Slf4j
public class SomeBean2 implements DisposableBean {
    @PreDestroy
    public void destroy1() {
        log.info("销毁1");
    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁2");
    }

    public void destroy3() {
        log.info("销毁3");
    }

}
