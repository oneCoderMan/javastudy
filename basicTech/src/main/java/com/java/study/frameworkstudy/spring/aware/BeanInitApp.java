package com.java.study.frameworkstudy.spring.aware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author： yijun
 * @DATE: 2023/12/5 08:24
 * @Description
 */
@SpringBootApplication
public class BeanInitApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BeanInitApp.class, args);
        run.close();

    }

    @Bean(initMethod = "init3")
    public SomeBean1 bean1() {
        return new SomeBean1();
    }

    @Bean(destroyMethod = "destroy3")
    public SomeBean2 bean2() {
        return new SomeBean2();
    }

}
