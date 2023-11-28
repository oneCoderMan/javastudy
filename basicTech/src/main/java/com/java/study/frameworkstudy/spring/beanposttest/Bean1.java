package com.java.study.frameworkstudy.spring.beanposttest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @Author： yijun
 * @DATE: 2023/11/27 22:28
 * @Description
 */
@Slf4j
public class Bean1 {
    private Bean2 bean2;
    @Autowired
    private Bean3 bean4;
    private Bean3 bean3;
    private String home;

    @Autowired
    public void setBean2(Bean2 bean2) {
        System.out.println("@Autowired 生效: " + bean2);
        this.bean2 = bean2;
    }

    @Resource
    public void setBean3(Bean3 bean3) {
        System.out.println("@Resource 生效: " + bean3);
        this.bean3 = bean3;
    }

    @Autowired
    public void setHome(@Value("${test.value}") String home) {
        System.out.println("@Value 生效: " + home);
        this.home = home;
    }

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 生效");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("@PreDestroy 生效");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", bean4=" + bean4 +
                ", home='" + home + '\'' +
                '}';
    }
}
