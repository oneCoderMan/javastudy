package com.java.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author： yijun
 * @DATE: 2023/9/23 20:09
 * @Description
 */
@SpringBootApplication
public class BasicTechApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BasicTechApplication.class, args);
        run.getBean("test");
    }
}
