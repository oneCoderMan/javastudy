package com.java.study.frameworkstudy.springboot;

import com.java.study.BasicTechApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;

/**
 * @Author： yijun
 * @DATE: 2023/11/21 08:13
 * @Description
 */
@SpringBootApplication
public class SpringBootDemo {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext run = SpringApplication.run(BasicTechApplication.class, args);
        System.out.println("success launch");
        String hi = run.getMessage("hi", null, Locale.CHINA);
        System.out.println(hi);
        // ApplicationContext 资源解析能力
        // 去当前工程的资源目录找
        Resource resource = run.getResource("classpath:application.yml");
        System.out.println(resource);
        // 去资源目录找，包括引入的Jar包
        Resource[] resources = run.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource1 : resources) {
            System.out.println(resource1);
        }
        // 获取到配置信息
        ConfigurableEnvironment environment = run.getEnvironment();
        System.out.println(environment.getProperty("java_home"));
        System.out.println(environment.getProperty("server.port"));

    }
}
