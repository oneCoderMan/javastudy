package com.java.study.frameworkstudy.springboot.beancycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author： yijun
 * @DATE: 2023/11/23 22:19
 * @Description
 */
@Component
public class LifeCycleBean {

    public LifeCycleBean() {
        System.out.println("构造函数");
    }

    @Autowired
    public void autowire(@Value("${test.value}") String home) {
        System.out.println("依赖注入" + home);
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁");
    }



}
