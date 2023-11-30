package com.java.study.frameworkstudy.spring.beanfacoryposttest.component;

import org.springframework.stereotype.Component;

/**
 * @Author： yijun
 * @DATE: 2023/11/29 22:22
 * @Description
 */
@Component
public class BFBean {
    public BFBean() {
        System.out.println("BFBean 我被 Spring 管理啦");
    }
}
