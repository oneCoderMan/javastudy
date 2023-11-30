package com.java.study.frameworkstudy.spring.beanfacoryposttest.component;

import org.springframework.stereotype.Controller;

/**
 * @Author： yijun
 * @DATE: 2023/11/30 22:02
 * @Description
 */
@Controller
public class BFBean3 {
    public BFBean3() {
        System.out.println("BFBean3 我被 Spring 管理啦");
    }
}
