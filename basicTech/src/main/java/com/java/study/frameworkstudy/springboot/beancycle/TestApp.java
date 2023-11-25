package com.java.study.frameworkstudy.springboot.beancycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/11/23 22:24
 * @Description
 */
@SpringBootApplication
public class TestApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TestApp.class, args);
        // ctx.close();
    }
}
